package com.construction.service.models.engineers;

import com.construction.data.models.Gender;
import com.construction.data.models.Project;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EngineerDetailsServiceModel {
    private long id;
    private String name;
    private Gender gender;
    private int rating;
    private int experienceYears;
    private List<Project> projects;

}
