package com.construction.service.models.companies;

import com.construction.data.models.Project;
import com.construction.data.models.Structure;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompanyDetailsServiceModel {

    private String name;
    private Structure structureType;
    private int rating;
    private int experienceYears;
    private Project project;

}
