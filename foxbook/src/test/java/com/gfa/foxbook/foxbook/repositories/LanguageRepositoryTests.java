package com.gfa.foxbook.foxbook.repositories;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase
public class LanguageRepositoryTests {

    private LanguageRepository languageRepository;

}
