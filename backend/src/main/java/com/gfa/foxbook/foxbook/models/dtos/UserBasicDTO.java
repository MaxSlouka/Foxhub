package com.gfa.foxbook.foxbook.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBasicDTO {
    private String firstName;
    private String lastName;
    private String email;
}
