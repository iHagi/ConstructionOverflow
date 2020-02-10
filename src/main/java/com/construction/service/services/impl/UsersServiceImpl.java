package com.construction.service.services.impl;

import com.construction.data.models.Company;
import com.construction.data.models.Engineer;
import com.construction.data.models.Project;
import com.construction.data.models.User;
import com.construction.data.repositories.ProjectsRepository;
import com.construction.data.repositories.UsersRepository;
import com.construction.service.models.auth.LoginUserServiceModel;
import com.construction.service.models.companies.CompanyCreateServiceModel;
import com.construction.service.models.engineers.EngineerCreateServiceModel;
import com.construction.service.models.projects.ProjectCreateServiceModel;
import com.construction.service.models.projects.ProjectServiceModel;
import com.construction.service.services.ProjectsService;
import com.construction.service.services.UsersService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final ProjectsService projectsService;
    private final ModelMapper mapper;

    public UsersServiceImpl(UsersRepository usersRepository, ProjectsService projectsService, ModelMapper mapper) {
        this.usersRepository = usersRepository;
        this.projectsService = projectsService;
        this.mapper = mapper;
    }

    @Override
    public void createProjectForUser(String username, ProjectCreateServiceModel model) {
        Optional<User> userOptional = usersRepository.findByUsername(username);

        if(userOptional.isEmpty()){
            throw new NullPointerException("User does not exist");
        }

        User user = userOptional.get();

        if(user.getProjects().isEmpty()){
            List<Project> projects = new ArrayList<>();
            user.setProjects(projects);
            Project project = projectsService.create(model);

            user.getProjects().add(project);
            project.setUser(user);

        } else {

            Project project = projectsService.create(model);

            user.getProjects().add(project);
            project.setUser(user);

        }
        usersRepository.save(user);

    }

    @Override
    public List<ProjectServiceModel> getProjectsForUser(String username) {
        Optional<User> userOptional = usersRepository.findByUsername(username);

        if(userOptional.isEmpty()){
            throw new NullPointerException("User does not exist");
        }
        User user = userOptional.get();

        return user.getProjects().stream().map(project->{
            ProjectServiceModel serviceModel = mapper.map(project, ProjectServiceModel.class);
            if(project.getEngineers()!=null){
                serviceModel.setOwnedEngineer(true);
            } else {
                serviceModel.setOwnedEngineer(false);
            }
            if(project.getCompany()!=null){

                serviceModel.setOwnedCompany(true);
            } else {
                serviceModel.setOwnedCompany(false);
            }

            return serviceModel;
        })
                .collect(Collectors.toList());
    }


    @Override
    public LoginUserServiceModel getUserByUsername(String name) {
        Optional<User> userOptional = usersRepository.findByUsername(name);

        if(userOptional.isEmpty()){
            throw new NullPointerException("User does not exist");
        }
        User user = userOptional.get();

        return mapper.map(user,LoginUserServiceModel.class);
    }


}
