package com.construction.service.models.engineers;

import com.construction.data.models.Structure;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EngineerProjectServiceModel {

    private String name;
    private String location;
    private Structure structure;
    private int squaredMeters;
    private int complexity;
    private int engineerCapacity;
}
