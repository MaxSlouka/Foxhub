package com.gfa.foxbook.foxbook.models.dtos;

import com.gfa.foxbook.foxbook.models.User;
import com.gfa.foxbook.foxbook.models.WorkPreference;
import com.gfa.foxbook.foxbook.models.nonusermodels.Language;
import com.gfa.foxbook.foxbook.models.nonusermodels.Personality;
import com.gfa.foxbook.foxbook.models.nonusermodels.Role;
import com.gfa.foxbook.foxbook.models.nonusermodels.Technology;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String nickname;
    private String workLocation;
    private WorkPreference workPreference;
    private String oneLineAbout;
    private String email;
    private String phone;
    private String about;
    private int yearOfBirth;
    private Personality personality;
    private String profilePictureUrl;
    private String location;
    private String facebook;
    private String instagram;
    private String linkedin;
    private String gitHub;
    private String optionalPage;
    private List<Role> roles;
    private List<Technology> technologies;
    private List<Language> languages;
    private boolean workStatus;

    public UserProfileDTO(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.about = user.getAbout();
        this.personality = user.getPersonality();
        this.location = user.getLocation();
        this.facebook = user.getFacebook();
        this.instagram = user.getInstagram();
        this.linkedin = user.getLinkedin();
        this.gitHub = user.getGitHub();
        this.optionalPage = user.getOptionalPage();
        this.roles = user.getRoles();
        this.technologies = user.getTechnologies();
        this.languages = user.getLanguages();
        this.profilePictureUrl = user.getProfilePictureUrl();
        this.workStatus =user.isWorkStatus();
        this.yearOfBirth=user.getYearOfBirth();
        this.oneLineAbout=user.getOneLineAbout();
        this.workLocation=user.getWorkLocation();
        this.workPreference= user.getWorkPreference();
    }
}
