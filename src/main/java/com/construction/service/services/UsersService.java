package com.construction.service.services;

import com.construction.data.models.Company;
import com.construction.data.models.Engineer;
import com.construction.data.models.Project;
import com.construction.service.models.auth.LoginUserServiceModel;
import com.construction.service.models.companies.CompanyCreateServiceModel;
import com.construction.service.models.engineers.EngineerCreateServiceModel;
import com.construction.service.models.projects.ProjectCreateServiceModel;
import com.construction.service.models.projects.ProjectServiceModel;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UsersService {

    void createProjectForUser(String username, ProjectCreateServiceModel model);

    List<ProjectServiceModel> getProjectsForUser(String username);

    LoginUserServiceModel getUserByUsername(String name);
}
