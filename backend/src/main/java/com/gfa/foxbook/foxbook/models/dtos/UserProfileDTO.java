package com.gfa.foxbook.foxbook.models.dtos;

import com.gfa.foxbook.foxbook.models.User;
import com.gfa.foxbook.foxbook.models.nonusermodels.Language;
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

    private String firstName;
    private String lastName;
    private String nickname;
    private String email;
    private String telephone;
    private String about;
    private String completeProjects;
    private String yearsOfExperience;
    private int yearOfBirth;
    private String personality;
    private String countryResidence;
    private String facebookURL;
    private String instagramURL;
    private String linkedInURL;
    private String gitHubURL;
    private String optionalPageURL;
    private List<Role> roles;
    private List<Technology> technologies;
    private List<Language> languages;

    UserProfileDTO(User user){
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.telephone = user.getTelephone();
        this.about = user.getAbout();
        this.completeProjects = user.getCompleteProjects();
        this.yearsOfExperience = user.getYearsOfExperience();
        this.personality = user.getPersonality();
        this.countryResidence = user.getCountryResidence();
        this.facebookURL = user.getFacebookURL();
        this.instagramURL = user.getInstagramURL();
        this.linkedInURL = user.getLinkedInURL();
        this.gitHubURL = user.getGitHubURL();
        this.optionalPageURL = user.getOptionalPageURL();
        this.technologies = user.getTechnologies();
        this.languages = user.getLanguages();
    }
}
