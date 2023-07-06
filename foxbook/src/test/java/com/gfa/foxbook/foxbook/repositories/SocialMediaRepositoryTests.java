package com.gfa.foxbook.foxbook.repositories;

import com.gfa.foxbook.foxbook.models.SocialMedia;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase // there is problem with DB connection - need to fix that
public class SocialMediaRepositoryTests {

    @Autowired
    private SocialMediaRepository socialMediaRepository;

    @Test
    public void testSaveSocialMedia() {
        SocialMedia socialMedia = new SocialMedia("Test-Social-Media", "https://test-social-media.com");

        SocialMedia savedSocialMedia = socialMediaRepository.save(socialMedia);

        Assertions.assertThat(savedSocialMedia.getId()).isNotNull();
        Assertions.assertThat(savedSocialMedia.getName()).isEqualTo("Test-Social-Media");
        Assertions.assertThat(savedSocialMedia.getUrl()).isEqualTo("https://test-social-media.com");
    }

    @Test
    public void testGetSocialMedia() {
        SocialMedia socialMedia = new SocialMedia("Test-Social-Media", "https://test-social-media.com");
        SocialMedia savedSocialMedia = socialMediaRepository.save(socialMedia);

        Optional<SocialMedia> optionalSocialMedia = socialMediaRepository.findById(savedSocialMedia.getId());

        Assertions.assertThat(optionalSocialMedia).isPresent();
        SocialMedia foundSocialMedia = optionalSocialMedia.get();
        Assertions.assertThat(foundSocialMedia.getName()).isEqualTo("Test-Social-Media");
        Assertions.assertThat(foundSocialMedia.getUrl()).isEqualTo("https://test-social-media.com");
    }

    @Test
    public void testUpdateSocialMedia() {
        SocialMedia socialMedia = new SocialMedia("Test-Social-Media", "https://test-social-media.com");
        SocialMedia savedSocialMedia = socialMediaRepository.save(socialMedia);

        savedSocialMedia.setName("Updated Test-Social-Media");
        savedSocialMedia.setUrl("https://test-social-media.com/updated");
        SocialMedia updatedSocialMedia = socialMediaRepository.save(savedSocialMedia);

        Assertions.assertThat(updatedSocialMedia.getId()).isEqualTo(savedSocialMedia.getId());
        Assertions.assertThat(updatedSocialMedia.getName()).isEqualTo("Updated Test-Social-Media");
        Assertions.assertThat(updatedSocialMedia.getUrl()).isEqualTo("https://test-social-media.com/updated");
    }

    @Test
    public void testDeleteSocialMedia() {
        SocialMedia socialMedia = new SocialMedia("Test-Social-Media", "https://test-social-media.com");
        SocialMedia savedSocialMedia = socialMediaRepository.save(socialMedia);

        socialMediaRepository.delete(savedSocialMedia);

        Optional<SocialMedia> optionalSocialMedia = socialMediaRepository.findById(savedSocialMedia.getId());
        Assertions.assertThat(optionalSocialMedia).isEmpty();
    }
}
