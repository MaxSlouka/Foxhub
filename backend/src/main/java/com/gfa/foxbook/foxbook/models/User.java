package com.gfa.foxbook.foxbook.models;
import com.gfa.foxbook.foxbook.models.User;
import com.gfa.foxbook.foxbook.models.nonusermodels.*;

import com.gfa.foxbook.foxbook.models.nonusermodels.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String verificationToken;
    private boolean verified;
    private String firstName;
    private String lastName;
    private String nickname;
    @NotNull
    @Column(unique = true)
    @Email
    private String email;
    private String phone;
    private String location;
    @Column(columnDefinition = "text")
    @Lob
    private String about;
    private int yearOfBirth;
    @NotNull
    private String password;
    private String profilePictureUrl;
    private String facebook;
    private String instagram;
    private String linkedin;
    private String gitHub;
    private boolean workStatus;
    private String optionalPage;
    private String oneLineAbout;
    private String workLocation;
    @Enumerated(EnumType.STRING)
    private WorkPreference workPreference;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "users_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "roles_id", referencedColumnName = "id")})
    private List<Role> roles;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "users_technologies",
            joinColumns = {@JoinColumn(name = "users_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "technologies_id", referencedColumnName = "id")})
    private List<Technology> technologies;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "user_languages",
            joinColumns = {@JoinColumn(name = "users_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "lang_id", referencedColumnName = "id")}
    )
    private List<Language> languages;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "users_personality",
            joinColumns = {@JoinColumn(name = "users_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "personality_id", referencedColumnName = "id")}
    )
    private Personality personality;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "users_colorPersonality",
            joinColumns = {@JoinColumn(name = "users_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "colorPersonality_id", referencedColumnName = "id")}
    )
    private ColorPersonality colorPersonality;

    public boolean isAdmin() {
        return roles.stream().anyMatch(role -> role.getName().equals("ADMIN"));

    }

    public Role getRole() {
        return roles.get(0);
    }


    public Like getLike() {
        return new Like(this);
    }

    private boolean hasVoted = false;

}

