package com.gfa.foxbook.foxbook.models.dtos;


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
    private String email;

    private String about;
    private List<Technology> technologies;

}
