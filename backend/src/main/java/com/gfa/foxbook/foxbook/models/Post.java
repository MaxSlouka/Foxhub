package com.gfa.foxbook.foxbook.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @NotBlank(message = "Title cannot be empty")
    private String title;
    private String author;
    private String authorPic;
    @NotBlank(message = "Content cannot be empty")
    private String content;
    private Integer likes;
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
