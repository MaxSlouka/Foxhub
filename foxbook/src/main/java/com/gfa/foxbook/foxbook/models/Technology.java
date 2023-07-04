package com.gfa.foxbook.foxbook.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "technologies")
public class Technology {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    public static List<String> getTechnologyList() {
        return List.of(
                "Java",
                "Angular",
                "Python",
                "React",
                "Node.js",
                "Ruby",
                "C++",
                "HTML",
                "CSS",
                "JavaScript"
        );
    }
}
