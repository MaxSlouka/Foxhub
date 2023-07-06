package com.gfa.foxbook.foxbook.repositories;

import com.gfa.foxbook.foxbook.models.Language;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase
public class LanguageRepositoryTests {

    @Autowired
    private LanguageRepository languageRepository;

    @Test
    public void testSaveLanguage() {
        Language language = new Language("TEST_LANGUAGE");

        Language savedLanguage = languageRepository.save(language);

        Assertions.assertThat(savedLanguage.getId()).isNotNull();
        Assertions.assertThat(savedLanguage.getName()).isEqualTo("TEST_LANGUAGE");
    }

    @Test
    public void testGetLanguage() {
        Language language = new Language("TEST_LANGUAGE");
        Language savedLanguage = languageRepository.save(language);

        Optional<Language> optionalLanguage = languageRepository.findById(savedLanguage.getId());

        Assertions.assertThat(optionalLanguage).isPresent();
        Language foundLanguage = optionalLanguage.get();
        Assertions.assertThat(foundLanguage.getName()).isEqualTo("TEST_LANGUAGE");
    }

    @Test
    public void testUpdateLanguage() {
        Language language = new Language("TEST_LANGUAGE");
        Language savedLanguage = languageRepository.save(language);

        savedLanguage.setName("UPDATED_LANGUAGE");
        Language updatedLanguage = languageRepository.save(savedLanguage);

        Assertions.assertThat(updatedLanguage.getId()).isEqualTo(savedLanguage.getId());
        Assertions.assertThat(updatedLanguage.getName()).isEqualTo("UPDATED_LANGUAGE");
    }

    @Test
    public void testDeleteLanguage() {
        Language language = new Language("TEST_LANGUAGE");
        Language savedLanguage = languageRepository.save(language);

        languageRepository.delete(savedLanguage);

        Optional<Language> optionalLanguage = languageRepository.findById(savedLanguage.getId());
        Assertions.assertThat(optionalLanguage).isEmpty();
    }

    @Test
    public void testGetAllLanguages() {
        List<Language> languages = Language.getLanguageList();
        languageRepository.saveAll(languages);

        List<Language> allLanguages = languageRepository.findAll();

        Assertions.assertThat(allLanguages).isNotEmpty();
        Assertions.assertThat(allLanguages).containsExactlyInAnyOrderElementsOf(languages);
    }
}
