//package com.gfa.foxbook.foxbook.controllers;
//
//import com.gfa.foxbook.foxbook.models.User;
//import com.gfa.foxbook.foxbook.services.interfaces.UserService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.mockito.Mockito.*;
//
//public class ApiControllerTests {
//
//    @Mock
//    private UserService userService;
//
//    private ApiController apiController;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        apiController = new ApiController(userService);
//    }
//
//    @Test
//    public void testPersonDetails_ValidId_ReturnsUser() {
//        // Arrange
//        Long userId = 1L;
//        User user = new User(userId, "JohnDoe");
//        when(userService.findById(userId)).thenReturn(Optional.of(user));
//
//        // Act
//        ResponseEntity<?> response = apiController.personDetails(userId);
//
//        // Assert
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(user, response.getBody());
//    }
//
//
//
//    @Test
//    public void testPersonDetails_InvalidId_ReturnsNotFound() {
//        // Arrange
//        Long userId = 1L;
//        when(userService.findById(userId)).thenReturn(Optional.empty());
//
//        // Act
//        ResponseEntity<?> response = apiController.personDetails(userId);
//
//        // Assert
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        assertNull(response.getBody());
//    }
//
//    @Test
//    public void testDeletePerson_ValidId_DeletesUser() {
//        // Arrange
//        Long userId = 1L;
//        User user = new User(userId, "JohnDoe");
//        when(userService.findById(userId)).thenReturn(Optional.of(user));
//
//        // Act
//        ResponseEntity<?> response = apiController.deletePerson(userId);
//
//        // Assert
//        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
//        verify(userService, times(1)).delete(user);
//    }
//
//    @Test
//    public void testDeletePerson_InvalidId_ReturnsNotFound() {
//        // Arrange
//        Long userId = 1L;
//        when(userService.findById(userId)).thenReturn(Optional.empty());
//
//        // Act
//        ResponseEntity<?> response = apiController.deletePerson(userId);
//
//        // Assert
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        assertEquals(null, response.getBody());
//        verify(userService, never()).delete(any());
//    }
//
//    @Test
//    public void testGetAllUsers_ReturnsAllUsers() {
//        // Arrange
//        List<User> users = Arrays.asList(new User(1L, "JohnDoe"), new User(2L, "JaneSmith"));
//        when(userService.getAll()).thenReturn(users);
//
//        // Act
//        ResponseEntity<?> response = apiController.getAllUsers();
//
//        // Assert
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(users, response.getBody());
//    }
//
//    @Test
//    public void testUpdateUserByNickname_ValidNickname_UpdatesUser() {
//        // Arrange
//        String nickname = "JohnDoe";
//        User user = new User(1L, nickname);
//        when(userService.findByNickname(nickname)).thenReturn(Optional.of(user));
//
//        // Act
//        ResponseEntity<?> response = apiController.updateUserByNickname(user, nickname);
//
//        // Assert
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        verify(userService, times(1)).updateProfile(user);
//    }
//
//    @Test
//    public void testUpdateUserByNickname_InvalidNickname_ReturnsNotFound() {
//        // Arrange
//        String nickname = "JohnDoe";
//        when(userService.findByNickname(nickname)).thenReturn(Optional.empty());
//        User user = new User(1L, nickname);
//
//        // Act
//        ResponseEntity<?> response = apiController.updateUserByNickname(user, nickname);
//
//        // Assert
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        assertEquals(null, response.getBody());
//        verify(userService, never()).updateProfile(any());
//    }
//}
