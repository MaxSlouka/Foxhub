package com.gfa.foxbook.foxbook.repositories;

import com.gfa.foxbook.foxbook.models.Role;
import com.gfa.foxbook.foxbook.models.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase
public class RoleRepositoryTests {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testSaveRole() {
        Role role = new Role("TEST_ROLE");

        Role savedRole = roleRepository.save(role);

        Assertions.assertThat(savedRole.getId()).isNotNull();
        Assertions.assertThat(savedRole.getName()).isEqualTo("TEST_ROLE");
    }

    @Test
    public void testFindByName() {
        Role role = new Role("TEST_ROLE");
        roleRepository.save(role);

        Optional<Role> optionalRole = roleRepository.findByName("TEST_ROLE");

        Assertions.assertThat(optionalRole).isPresent();
        Role foundRole = optionalRole.get();
        Assertions.assertThat(foundRole.getName()).isEqualTo("TEST_ROLE");
    }
}
