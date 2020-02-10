package com.construction.data.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "engineers")
public class Engineer extends BaseEntity {

    @Column(unique = true)
    @NotNull
    @Size(min = 3, max = 20)
    private String name;
    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column
    @Min(value = 0)
    private int rating;
    @Column
    @Min(value = 1)
    private int experienceYears;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "engineer_projects",
            joinColumns = {@JoinColumn(name = "engineer_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")}
    )
    private List<Project> projects;

}
