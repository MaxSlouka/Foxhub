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
public class UserSearchDTO {
    private String firstName;
    private String lastName;
    private String nickname;
    private String about;
    private Boolean workStatus;
    private Integer yearOfBirth;
    private Personality personality;
    private Boolean verified;
    private Role role;
    private String location;
    private String profilePictureUrl;
    private List<Technology> technologies;
    private List<Language> languages;
    private String workLocation;
    private WorkPreference workPreference;
    private String oneLineAbout;

    public UserSearchDTO(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.nickname = user.getNickname();
        this.about = user.getAbout();
        this.yearOfBirth = user.getYearOfBirth();
        this.technologies = user.getTechnologies();
        this.profilePictureUrl = user.getProfilePictureUrl();
        this.languages = user.getLanguages();
        this.workStatus = user.isWorkStatus();
        this.personality = user.getPersonality();
        this.verified = user.isVerified();
        this.role = user.getRole();
        this.location = user.getLocation();
        this.workLocation = user.getWorkLocation();
        this.workPreference = user.getWorkPreference();
        this.oneLineAbout = user.getOneLineAbout();
    }
}
