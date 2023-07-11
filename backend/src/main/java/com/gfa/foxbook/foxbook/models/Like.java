package com.gfa.foxbook.foxbook.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_likes")
public class Like {
    public Long getUserId() {
        return userId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long postId;
    private Long userId;
    private Integer likes;
    private Boolean hasVoted;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
