package com.gfa.foxbook.foxbook.repositories;

import com.gfa.foxbook.foxbook.models.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest // Spring Boot should configure the test environment and provide an in-memory database for testing purposes.
@AutoConfigureTestDatabase // should automatically configure the test database based on project's database configuration
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    // but there is a problem with DB connection, tests always fail

    @Test
    public void UserRepository_SaveAll_ReturnSavedUser() {
        User user = User.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .password("qwe123")
                .build();

        User savedUser = userRepository.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void UserRepository_GetAll_ReturnMoreThanOneUser() {
        User user = User.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .password("qwe123")
                .build();

        User user2 = User.builder()
                .firstName("Jane")
                .lastName("Doe")
                .email("jane@example.com")
                .password("qwe123")
                .build();

        userRepository.save(user);
        userRepository.save(user2);

        List<User> userList = userRepository.findAll();

        Assertions.assertThat(userList).isNotNull();
        Assertions.assertThat(userList.size()).isEqualTo(2);
    }

    @Test
    public void UserRepository_FindById_ReturnUser() {
        User user = User.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .password("qwe123")
                .build();

        userRepository.save(user);

        User userSave = userRepository.findById(user.getId()).get();

        Assertions.assertThat(userSave).isNotNull();
    }

    @Test
    public void UserRepository_FindByEmail_ReturnUserNotNull() {
        User user = User.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .password("qwe123")
                .build();

        userRepository.save(user);

        User userSave = userRepository.findByEmail(user.getEmail()).get();

        Assertions.assertThat(userSave).isNotNull();
    }

    @Test
    public void UserRepository_ExistsByEmail_ReturnUserNotNull() {
        User user = User.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .password("qwe123")
                .build();

        userRepository.save(user);

        boolean existsByEmail = userRepository.existsByEmail(user.getEmail());

        Assertions.assertThat(existsByEmail).isTrue();
    }

    @Test
    public void UserRepository_FindByNickname_ReturnUserNotNull() {
        User user = User.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .password("qwe123")
                .build();

        userRepository.save(user);

        User userSave = userRepository.findByNickname(user.getNickname()).get();

        Assertions.assertThat(userSave).isNotNull();
    }

    @Test
    public void UserRepository_UpdateUser_ReturnUserNotNull() {
        User user = User.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .password("qwe123")
                .build();

        userRepository.save(user);

        User userSave = userRepository.findById(user.getId()).get();
        userSave.setFirstName("Joseph");
        userSave.setLastName("Coolio");

        User updatedUser = userRepository.save(userSave);

        Assertions.assertThat(updatedUser.getFirstName()).isNotNull();
        Assertions.assertThat(updatedUser.getLastName()).isNotNull();
    }

    @Test
    public void UserRepository_UserDelete_ReturnUserIsEmpty() {
        User user = User.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .password("qwe123")
                .build();

        userRepository.save(user);

        userRepository.deleteById(user.getId());
        Optional<User> userReturn = userRepository.findById(user.getId());

        Assertions.assertThat(userReturn).isEmpty();
    }
}
