package com.gfa.foxbook.foxbook.models.dtos;

import lombok.Data;

@Data
public class ResponseDTO {
    private String status;

    public ResponseDTO(String status) {
        this.status = status;
    }
}
