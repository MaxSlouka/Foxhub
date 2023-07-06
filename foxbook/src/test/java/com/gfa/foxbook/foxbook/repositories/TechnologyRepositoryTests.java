package com.gfa.foxbook.foxbook.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase // need to fix the DB connection - tests always fail
public class TechnologyRepositoryTests {

    @Autowired
    private TechnologyRepository technologyRepository;


}
