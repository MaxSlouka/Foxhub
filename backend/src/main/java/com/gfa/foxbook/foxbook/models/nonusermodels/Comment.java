package com.gfa.foxbook.foxbook.models.nonusermodels;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private Long postId;
    private String username;
    private String content;
    private Long userId;
    @ManyToOne
    @JoinTable(
            name = "prcat",
            joinColumns = {@JoinColumn(name = "comments", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "post_id", referencedColumnName = "id")})
    private Post post;
    public void setPost(Post post) {
        this.postId = post.getId();
    }
}
