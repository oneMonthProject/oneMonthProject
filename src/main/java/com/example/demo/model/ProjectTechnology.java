package com.example.demo.model;

import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "project_technology")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ProjectTechnology {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "project_technology_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "technology_stack_id")
    private TechnologyStack technologyStack;
}
