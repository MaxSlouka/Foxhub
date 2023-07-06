package com.gfa.foxbook.foxbook.services;

import com.gfa.foxbook.foxbook.models.User;
import com.gfa.foxbook.foxbook.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testFindById() {
        User user = new User();
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> result = userService.findById(1L);

        assertThat(result).isEqualTo(Optional.of(user));
        verify(userRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testDelete() {
        User user = new User();

        userService.delete(user);

        verify(userRepository, times(1)).delete(user);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testFindAll() {
        User user1 = new User();
        User user2 = new User();
        List<User> users = Arrays.asList(user1, user2);
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.findAll();

        assertThat(result).isEqualTo(users);
        verify(userRepository, times(1)).findAll();
        verifyNoMoreInteractions(userRepository);
    }
}
