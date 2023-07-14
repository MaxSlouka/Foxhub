package com.gfa.foxbook.foxbook.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.Text;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    private String firstName;
    private String lastName;
    private String nickname;
    @NotNull
    @Column(unique = true)
    @Email
    private String email;
    private String telephone;
    private String about;
    private String completeProjects;
    private String yearsOfExperience;
    private int yearOfBirth;
    @NotNull
    private String password;
    private String personality;
    private String countryResidence;
    private Date dateOfRegistration;
    private String profileUrl;
    private String profilePictureUrl;
    private String facebookURL;
    private String instagramURL;
    private String linkedInURL;
    private String gitHubURL;
    private String optionalPageURL;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "users_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "roles_id", referencedColumnName = "id")})
    private List<Role> roles;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_technologies",
            joinColumns = {@JoinColumn(name = "users_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "technologies_id", referencedColumnName = "id")})
    private List<Technology> technologies;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_languages",
            joinColumns = {@JoinColumn(name = "users_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "languages_id", referencedColumnName = "id")})
    private List<Language> languages;


}
