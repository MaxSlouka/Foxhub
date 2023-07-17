package com.gfa.foxbook.foxbook.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDTO {
    String firstName;
    String lastName;
    String email;

    String github;
    String linkedin;
    String facebook;
    String instagram;
    String twitter;
}
