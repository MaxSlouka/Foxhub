package com.gfa.foxbook.foxbook.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String nickname;
    private String email;
    private int yearOfBirth;
    private String password;
    private String personality;
    private String countryResidence;
    private Date dateOfRegistration;
    private String profileUrl;
    private String profilePictureUrl;
    @ManyToMany
    private List<Role> roles;
    @ManyToMany
    private List<SocialMedia> socialMedia;
    private List<Technology> technologies;
    private List<Language> languages;

}
