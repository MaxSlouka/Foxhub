package com.gfa.foxbook.foxbook.models.nonusermodels;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private Long userId;
    @NotBlank(message = "Content cannot be empty")
    @Lob
    @Column(columnDefinition = "text")
    private String content;
    private LocalDateTime createdAt;
    private Integer likesCount;
}
