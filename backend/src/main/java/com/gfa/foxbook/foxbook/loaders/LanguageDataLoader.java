package com.gfa.foxbook.foxbook.loaders;

import com.gfa.foxbook.foxbook.models.nonusermodels.Language;
import com.gfa.foxbook.foxbook.repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LanguageDataLoader implements CommandLineRunner {

    private final LanguageRepository languageRepository;

    @Autowired
    public LanguageDataLoader(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (languageRepository.count() == 0) {
            // Load initial languages if the database is empty
            loadInitialLanguages();
        }
    }

    private void loadInitialLanguages() {
        // Add the initial languages here

        // English
        Language english = new Language();
        english.setName("English");
        languageRepository.save(english);

        // Spanish
        Language spanish = new Language();
        spanish.setName("Spanish");
        languageRepository.save(spanish);

        // Chinese
        Language chinese = new Language();
        chinese.setName("Chinese");
        languageRepository.save(chinese);

        // Arabic
        Language arabic = new Language();
        arabic.setName("Arabic");
        languageRepository.save(arabic);

        // Hindi
        Language hindi = new Language();
        hindi.setName("Hindi");
        languageRepository.save(hindi);

        // French
        Language french = new Language();
        french.setName("French");
        languageRepository.save(french);

        // Russian
        Language russian = new Language();
        russian.setName("Russian");
        languageRepository.save(russian);

        // Portuguese
        Language portuguese = new Language();
        portuguese.setName("Portuguese");
        languageRepository.save(portuguese);

        // Japanese
        Language japanese = new Language();
        japanese.setName("Japanese");
        languageRepository.save(japanese);

        // German
        Language german = new Language();
        german.setName("German");
        languageRepository.save(german);

        // Italian
        Language italian = new Language();
        italian.setName("Italian");
        languageRepository.save(italian);

        // Korean
        Language korean = new Language();
        korean.setName("Korean");
        languageRepository.save(korean);

        // Turkish
        Language turkish = new Language();
        turkish.setName("Turkish");
        languageRepository.save(turkish);

        // Dutch
        Language dutch = new Language();
        dutch.setName("Dutch");
        languageRepository.save(dutch);

        // Swedish
        Language swedish = new Language();
        swedish.setName("Swedish");
        languageRepository.save(swedish);

        // Greek
        Language greek = new Language();
        greek.setName("Greek");
        languageRepository.save(greek);
    }
}

