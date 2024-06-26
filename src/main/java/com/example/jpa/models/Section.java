package com.example.jpa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Section {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private int sectionOrder;
    @ManyToOne
    @JoinColumn(
            name = "course_id"
    )
    private Course course;
    @OneToMany(
            mappedBy = "section"
    )
    private List<Lecture> lectures;
}
