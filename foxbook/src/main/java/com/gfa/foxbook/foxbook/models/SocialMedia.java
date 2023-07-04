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

    public static List<SocialMedia> getSocialMediaList() {
        return List.of(
                new SocialMedia(1, "GitHub", "https://github.com"),
                new SocialMedia(2, "Facebook", "https://facebook.com"),
                new SocialMedia(3, "Instagram", "https://instagram.com"),
                new SocialMedia(4, "LinkedIn", "https://linkedin.com"),
                new SocialMedia(5, "Twitter", "https://twitter.com")
        );
    }
}
