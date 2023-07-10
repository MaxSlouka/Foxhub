package com.gfa.foxbook.foxbook.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String authorPic;
    private String content;
    private String timestamp;
    private Integer likes;
    private Boolean upVote;
    private Integer commentsCount;
    private String comments;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
