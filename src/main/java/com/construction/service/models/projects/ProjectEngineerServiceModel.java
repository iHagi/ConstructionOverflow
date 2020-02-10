package com.construction.service.models.projects;

import com.construction.data.models.Company;
import com.construction.data.models.Engineer;
import com.construction.data.models.Structure;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectEngineerServiceModel {

    private long id;
    private String name;
    private String location;
    private Structure structure;
    private int squaredMeters;
    private int complexity;
    private List<Engineer> engineers;
    private Company company;

}
