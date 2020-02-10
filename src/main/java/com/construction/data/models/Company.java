package com.construction.data.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "companies")
public class Company extends BaseEntity {

    @Column(unique = true)
    @NotNull
    @Size(min = 3, max = 20)
    private String name;
    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private Structure structureType;
    @Column
    @Min(value = 0)
    private int rating;
    @Column
    @Min(value = 1)
    private int experienceYears;

    @OneToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project;

}
