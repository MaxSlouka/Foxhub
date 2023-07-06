package com.gfa.foxbook.foxbook.repositories;

import com.gfa.foxbook.foxbook.models.Language;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase
public class LanguageRepositoryTests {

    private LanguageRepository languageRepository;

    @Test
    public void testSaveLanguage() {
        Language language = new Language("TEST_LANGUAGE");

        Language savedLanguage = languageRepository.save(language);

        Assertions.assertThat(savedLanguage.getId()).isNotNull();
        Assertions.assertThat(savedLanguage.getName()).isEqualTo("TEST_LANGUAGE");
    }
}
