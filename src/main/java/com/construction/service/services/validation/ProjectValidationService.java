package com.construction.service.services.validation;

import com.construction.service.models.projects.ProjectCreateServiceModel;

public interface ProjectValidationService {

    boolean isProjectValid(ProjectCreateServiceModel projectCreateServiceModel);

}
