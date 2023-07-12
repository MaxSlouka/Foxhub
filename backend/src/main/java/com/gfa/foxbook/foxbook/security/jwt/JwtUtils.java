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
        return generateCookie("token", jwt, "/", (int) (SecurityConstants.JWT_EXPIRATION_TIME / 1000));
    }
    public ResponseCookie generateJwtCookie(String username) {
        String jwt = generateTokenFromUsername(username);
        return generateCookie("token", jwt, "/", (int) (SecurityConstants.JWT_EXPIRATION_TIME / 1000));
    }
    public ResponseCookie generateRefreshJwtCookie(Authentication userPrincipal) {
        String jwt = generateRefreshTokenFromUsername(userPrincipal.getName());
        return generateCookie("refreshToken", jwt, "/", (int) (SecurityConstants.REFRESH_TOKEN_EXPIRATION_TIME/1000));
    }

    private ResponseCookie generateCookie(String name, String value, String path, int maxAge) {
        ResponseCookie cookie = ResponseCookie.from(name, value).path(path).maxAge(maxAge).httpOnly(true).build();
        return cookie;
    }
    public String generateRefreshTokenFromUsername(String username) {
        Claims extraClaims = new DefaultClaims();
        UUID uuid = UUID.randomUUID();
        User user = userRepository.findByEmail(username).get();
        user.setUuid(uuid);
        extraClaims.put("sub", username);
        extraClaims.put("jti", uuid.toString());
        userRepository.save(user);


        return Jwts.builder()
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
    public String getRefreshJwtFromCookies(HttpServletRequest request) {
        return getCookieValueByName(request, "refreshToken");
    }
    public String getUserNameFromJwtToken(String token) {
        String xxx = Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token).getBody().getSubject();
        // todo format back from testing format
        System.out.println(xxx); // todo delete

        return xxx;
    }
    public String getUserNameFromJwtRefreshToken(String token) {

        // need to get claims with username from token
        Claims claims = Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token).getBody();
        String username = claims.get("sub", String.class);
        System.out.println(claims);
        System.out.println(username);

        // todo format back from testing format

        return username;
    }
    public String getRefreshTokenValidateAndGenerateAccessToken(HttpServletRequest request){
        if(request.getCookies() == null || request.getCookies().length == 0){
            return null;
        }
        String refreshToken = getRefreshJwtFromCookies(request);
        if(validateJwtToken(refreshToken)){

            String username = getUserNameFromJwtRefreshToken(refreshToken);
            return username;
        }
        return null;

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
    // if token expired
        // any token
        // if yes check the refresh token
            // validity
            // expiration date
            // check refresh token UIID  to user uiid
                // if it's same -> ok
                // if not -> not ok
        // if both ok send new token

    // probably not needed
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION_TIME);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expirationDate)
                .signWith(key())
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
