package com.construction.data.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "projects")
public class Project extends BaseEntity {
    @Column(unique = true)
    @NotNull
    @Size(min = 3, max = 256)
    private String name;
    @Column
    @NotNull
    @Size(min = 3, max = 256)
    private String location;
    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private Structure structure;
    @Column
    @Min(value = 200)
    @Max(value = 90000)
    private int squaredMeters;
    @Column
    @Min(value = 1)
    @Max(value = 5)
    private int complexity;
    @Column
    @Min(value = 2)
    private int engineerCapacity;

    @ManyToMany(mappedBy = "projects")
    private List<Engineer> engineers;

    @OneToOne(mappedBy = "project")
    private Company company;

    @ManyToOne()
    @JoinColumn(referencedColumnName = "id", name = "user_id")
    private User user;
}
