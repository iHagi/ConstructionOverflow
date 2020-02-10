package com.construction.service.models.companies;

import com.construction.data.models.Gender;
import com.construction.data.models.Structure;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyCreateServiceModel {
    private String name;
    private Structure structureType;
    private int experienceYears;
}
