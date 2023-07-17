package com.gfa.foxbook.foxbook.repositories;

import com.gfa.foxbook.foxbook.models.nonusermodels.Technology;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

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

    @Test
    public void testGetTechnology() {
        Technology technology = new Technology("TestTechnology");
        Technology savedTechnology = technologyRepository.save(technology);

        Optional<Technology> optionalTechnology = technologyRepository.findById(savedTechnology.getId());

        Assertions.assertThat(optionalTechnology).isPresent();
        Technology foundTechnology = optionalTechnology.get();
        Assertions.assertThat(foundTechnology.getName()).isEqualTo("TestTechnology");
    }

    @Test
    public void testUpdateTechnology() {
        Technology technology = new Technology("TestTechnology");
        Technology savedTechnology = technologyRepository.save(technology);

        savedTechnology.setName("Updated TestTechnology");
        Technology updatedTechnology = technologyRepository.save(savedTechnology);

        Assertions.assertThat(updatedTechnology.getId()).isEqualTo(savedTechnology.getId());
        Assertions.assertThat(updatedTechnology.getName()).isEqualTo("Updated TestTechnology");
    }

    @Test
    public void testDeleteTechnology() {
        Technology technology = new Technology("TestTechnology");
        Technology savedTechnology = technologyRepository.save(technology);

        technologyRepository.delete(savedTechnology);

        Optional<Technology> optionalTechnology = technologyRepository.findById(savedTechnology.getId());
        Assertions.assertThat(optionalTechnology).isEmpty();
    }

    @Test
    public void testGetAllTechnologies() {
        List<Technology> technologies = Technology.getTechnologyList();
        technologyRepository.saveAll(technologies);

        List<Technology> allTechnologies = technologyRepository.findAll();

        Assertions.assertThat(allTechnologies).isNotEmpty();
        Assertions.assertThat(allTechnologies).containsExactlyInAnyOrderElementsOf(technologies);
    }
}
