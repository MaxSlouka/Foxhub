package com.gfa.foxbook.foxbook.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfa.foxbook.foxbook.models.dtos.ResponseDTO;
import com.gfa.foxbook.foxbook.models.dtos.security.AuthResponseDTO;
import com.gfa.foxbook.foxbook.models.dtos.security.LoginDto;
import com.gfa.foxbook.foxbook.models.dtos.security.RegisterDto;
import com.gfa.foxbook.foxbook.services.SecurityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(AuthController.class)
public class AuthControllerTests {


    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private SecurityService securityService;

    @InjectMocks
    private AuthController authController;

    private MockMvc mockMvc;

    public AuthControllerTests(AuthenticationManager authenticationManager,
                               SecurityService securityService,
                               AuthController authController,
                               MockMvc mockMvc) {
        this.authenticationManager = authenticationManager;
        this.securityService = securityService;
        this.authController = authController;
        this.mockMvc = mockMvc;
    }

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    public void testLogin() throws Exception {
        // Arrange
        String email = "test@example.com";
        String password = "password";
        String token = "sample_token";
        LoginDto loginDto = new LoginDto(email, password);
        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        AuthResponseDTO authResponseDTO = new AuthResponseDTO(token);

        when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(authentication);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(loginDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").value(token));
    }

    @Test
    public void testRegister() throws Exception {
        // Arrange
        String email = "test@example.com";
        RegisterDto registerDto = new RegisterDto(email, "password");
        ResponseDTO responseDTO = new ResponseDTO("User registered successfully");

        when(securityService.userExistsByEmail(email)).thenReturn(false);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(registerDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(responseDTO.getStatus()));
    }

    private String asJsonString(Object object) throws Exception {
        return new ObjectMapper().writeValueAsString(object);
    }
}
