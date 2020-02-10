package com.construction.service.models.engineers;

import com.construction.data.models.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EngineerCreateServiceModel {
    private String name;
    private Gender gender;
    private int experienceYears;

}
