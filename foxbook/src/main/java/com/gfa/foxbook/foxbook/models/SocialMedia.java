package com.gfa.foxbook.foxbook.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "social_media")
public class SocialMedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String url;

    public SocialMedia(String gitHub, String url) {
        this.name = gitHub;
        this.url = url;
    }

    public static List<SocialMedia> getSocialMediaList() {
        return List.of(
                new SocialMedia("GitHub", "https://github.com"),
                new SocialMedia("Facebook", "https://facebook.com"),
                new SocialMedia("Instagram", "https://instagram.com"),
                new SocialMedia("LinkedIn", "https://linkedin.com"),
                new SocialMedia("Twitter", "https://twitter.com")
        );
    }
}
