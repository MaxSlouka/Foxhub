package com.gfa.foxbook.foxbook.controllers;

import com.gfa.foxbook.foxbook.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = WebController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class WebControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    @Test
    public void registrationEndpoint_ReturnsRegistrationHtmlView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/registration"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("registration.html"));
    }

    @Test
    public void mainEndpoint_ReturnsMainHtmlView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/main"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("main.html"));
    }

    @Test
    public void profileEndpoint_ReturnsProfileHtmlView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/profile"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("profile.html"));
    }

    @Test
    public void getAllUsersEndpoint_ReturnsPeopleHtmlView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/people"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("people.html"));
    }


}
