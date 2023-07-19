package com.gfa.foxbook.foxbook.models.dtos;


import com.gfa.foxbook.foxbook.models.User;
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
    private List<Technology> technologies;

    public UserSearchDTO(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.nickname = user.getNickname();
        this.about = user.getAbout();
        this.technologies = user.getTechnologies();
    }
}
