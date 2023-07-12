package com.gfa.foxbook.foxbook.security.jwt;

import com.gfa.foxbook.foxbook.models.User;
import com.gfa.foxbook.foxbook.repositories.UserRepository;
import com.gfa.foxbook.foxbook.security.SecurityConstants;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    private final UserRepository userRepository;



    public ResponseCookie generateJwtCookie(Authentication userPrincipal) {
        String jwt = generateTokenFromUsername(userPrincipal.getName());
        return generateCookie("token", jwt, "/");
    }
    public ResponseCookie generateRefreshJwtCookie(Authentication userPrincipal) {
        String jwt = generateRefreshTokenFromUsername(userPrincipal.getName());
        return generateCookie("refreshToken", jwt, "/");
    }

    private ResponseCookie generateCookie(String name, String value, String path) {
        ResponseCookie cookie = ResponseCookie.from(name, value).path(path).maxAge(30 * 24 * 60 * 60).httpOnly(true).build();
        return cookie;
    }
    public String generateRefreshTokenFromUsername(String username) {
        Claims extraClaims = new DefaultClaims();
        UUID uuid = UUID.randomUUID();
        User user = userRepository.findByEmail(username).get();
        user.setUuid(uuid);
        extraClaims.put("jti", uuid.toString());
        userRepository.save(user);

        return Jwts.builder()
                .setSubject(username)
                .setClaims(extraClaims)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + SecurityConstants.REFRESH_TOKEN_EXPIRATION_TIME))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateTokenFromUsername(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + SecurityConstants.JWT_EXPIRATION_TIME))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String getJwtFromCookies(HttpServletRequest request) {
        return getCookieValueByName(request, "token");
    }
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    // probably not needed
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION_TIME);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expirationDate)
                .signWith(key()) // made changes here
                .compact();
        return token;
    }

    public String getUsernameFromJWT(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key()).build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
//            throw new AuthenticationCredentialsNotFoundException("JWT token is expired or invalid");
        }
    }

    private String getCookieValueByName(HttpServletRequest request, String name) {
        Cookie cookie = WebUtils.getCookie(request, name);
        if (cookie != null) {
            return cookie.getValue();
        } else {
            return null;
        }
    }

    private Key key() {
        return Keys.hmacShaKeyFor(SecurityConstants.JWT_SECRET);
    }
}
