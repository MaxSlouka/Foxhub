package com.gfa.foxbook.foxbook.repositories;

import com.gfa.foxbook.foxbook.models.Technology;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase // need to fix the DB connection - tests always fail
public class TechnologyRepositoryTests {

    @Autowired
    private TechnologyRepository technologyRepository;

    @Test
    public void testSaveTechnology() {
        Technology technology = new Technology("TestTechnology");

        Technology savedTechnology = technologyRepository.save(technology);

        Assertions.assertThat(savedTechnology.getId()).isNotNull();
        Assertions.assertThat(savedTechnology.getName()).isEqualTo("TestTechnology");
    }
}
