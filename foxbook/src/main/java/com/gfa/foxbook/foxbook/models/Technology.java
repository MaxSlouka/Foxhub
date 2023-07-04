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
                "JavaScript",
                "TypeScript",
                "SQL",
                "NoSQL",
                "MongoDB",
                "Spring",
                "Hibernate",
                "JPA",
                "Docker",
                "Kubernetes",
                "AWS",
                "Azure",
                "GCP",
                "Linux",
                "Windows",
                "MacOS",
                "iOS",
                "Android",
                "Flutter",
                "Kotlin",
                "Swift",
                "C#",
                "C",
                "Go",
                "Rust",
                "PHP",
                "Perl",
                "Scala",
                "Clojure",
                "Elixir",
                "Haskell",
                "R",
                "Julia",
                "Assembly",
                "Objective-C",
                "F#",
                "Dart",
                "Lua",
                "Bash",
                "PowerShell",
                "MATLAB",
                "COBOL",
                "Fortran",
                "Lisp",
                "Pascal",
                "Ada",
                "Scheme",
                "Prolog",
                "Smalltalk",
                "Logo",
                "RPG",
                "PL/I",
                "APL",
                "Forth",
                "Erlang",
                "Tcl",
                "Awk",
                "Visual Basic",
                "LabVIEW",
                "ABAP",
                "Delphi"
                );
    }
}
