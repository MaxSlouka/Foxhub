package com.gfa.foxbook.foxbook.models.nonusermodels;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long parentPostId;
    private String username;
    private Long usernameId;
    @NotBlank(message = "Content cannot be empty")
    private String content;
    private Timestamp createdAt;
    private Integer likesCount;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
