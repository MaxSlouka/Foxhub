package com.gfa.foxbook.foxbook.services;

import com.gfa.foxbook.foxbook.models.*;
import com.gfa.foxbook.foxbook.repositories.*;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@AllArgsConstructor
public class DatabaseFiller implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final SocialMediaRepository socialMediaRepository;
    private final TechnologyRepository technologyRepository;
    private final LanguageRepository languageRepository;

    @Override
    public void run(String... args) {
        Faker faker = new Faker();
        List<User> users = new ArrayList<>();


//        for (int i = 0; i < 20; i++) {
//            User user = new User();
//            user.setFirstName(faker.name().firstName());
//            user.setLastName(faker.name().lastName());
//            user.setNickname(faker.name().username());
//            user.setEmail(faker.internet().emailAddress());
//            user.setYearOfBirth(faker.number().numberBetween(1950, 2003));
//            user.setPassword(faker.internet().password());
//            user.setPersonality(faker.lorem().word());
//            user.setCountryResidence(faker.address().country());
//            // user.setDateOfRegistration(faker.date().past(365 * 10)); // Register within the past 10 years
//            user.setProfileUrl(faker.internet().url());
//            user.setProfilePictureUrl(faker.internet().image());
//
//            // Generate random roles, social media, technologies, and languages
//            user.setRoles((List<Role>) generateRandomRoles());
////            user.setSocialMedias(generateRandomSocialMedias());
////            user.setTechnologies(generateRandomTechnologies());
////            user.setLanguages(generateRandomLanguages());
//
//            users.add(user);
//        }
//
//        userRepository.saveAll(users);
        if(roleRepository.findAll().isEmpty()){
            roleRepository.saveAll(Role.getRoleList());
        }

        if(socialMediaRepository.findAll().isEmpty()){
            socialMediaRepository.saveAll(SocialMedia.getSocialMediaList());
        }

        if(technologyRepository.findAll().isEmpty()){
            technologyRepository.saveAll(Technology.getTechnologyList());
        }

        if(languageRepository.findAll().isEmpty()){
            languageRepository.saveAll(Language.getLanguageList());
        }
//
//    }
//
//    private Role generateRandomRoles() {
//        Random random = new Random();
//
//        boolean outcome = random.nextBoolean();
//
//        if (outcome) {
//            return roles.get(0);
//        } else {
//            return roles.get(1);
//        }
//    }
////
////    private List<SocialMedia> generateRandomSocialMedias() {
////        // Generate random social media
////    }
////
////    private List<Technology> generateRandomTechnologies() {
////        // Generate random technologies
////    }
////
////    private List<Language> generateRandomLanguages() {
////        // Generate random languages
    }
}
