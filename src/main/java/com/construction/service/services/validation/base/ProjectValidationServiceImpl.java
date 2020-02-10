package com.construction.service.services.validation.base;

import com.construction.service.models.projects.ProjectCreateServiceModel;
import com.construction.service.services.validation.ProjectValidationService;
import org.springframework.stereotype.Service;

@Service
public class ProjectValidationServiceImpl implements ProjectValidationService {

    @Override
    public boolean isProjectValid(ProjectCreateServiceModel serviceModel) {
        return serviceModel != null &&
                serviceModel.getName() != null &&
                serviceModel.getLocation() != null &&
                serviceModel.getStructure() !=null &&
                serviceModel.getComplexity()>0 &&
                serviceModel.getSquaredMeters()>0;
    }
}
