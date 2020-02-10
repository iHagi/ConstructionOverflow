package com.construction.service.services;

import com.construction.data.models.Project;
import com.construction.service.models.projects.ProjectCreateServiceModel;
import com.construction.service.models.projects.ProjectEngineerServiceModel;
import com.construction.service.models.projects.ProjectServiceModel;

import java.util.List;

public interface ProjectsService {

    ProjectEngineerServiceModel getById(long id);

    void addToEngineerById(long id);

    Project create(ProjectCreateServiceModel projectServiceModel);

    List<ProjectServiceModel> getAll();

    void addToCompanyById(long id);
}
