package com.gfa.foxbook.foxbook.models.nonusermodels;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be empty")
    private String title;
    private String author;
    private String authorPic;

    @NotBlank(message = "Content cannot be empty")
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes = new ArrayList<>();

    private Timestamp timestamp;
    private Integer commentsCount;
    private String comments;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public boolean isEmpty() {
        return false;
    }

    public void addComment(Comment newComment) {
        if (comments == null) {
            comments = "";
        }
        if (!comments.isEmpty()) {
            comments += "\n";
        }
        comments += newComment.getAuthor() + ": " + newComment.getContent();
        commentsCount++;
    }
}
