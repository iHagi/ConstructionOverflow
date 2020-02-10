package com.construction.service.services.impl;

import com.construction.data.models.Company;
import com.construction.data.models.Engineer;
import com.construction.data.models.Project;
import com.construction.data.repositories.CompaniesRepository;
import com.construction.data.repositories.EngineersRepository;
import com.construction.data.repositories.ProjectsRepository;
import com.construction.service.models.projects.ProjectCreateServiceModel;
import com.construction.service.models.projects.ProjectEngineerServiceModel;
import com.construction.service.models.projects.ProjectServiceModel;
import com.construction.service.services.ProjectsService;
import com.construction.service.services.validation.ProjectValidationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectsService {

    private final ProjectsRepository projectsRepository;
    private final EngineersRepository engineerRepository;
    private final CompaniesRepository companiesRepository;
    private final ProjectValidationService projectValidationService;
    private final ModelMapper modelMapper;

    @Override
    public ProjectEngineerServiceModel getById(long id) {

        return modelMapper.map(projectsRepository.getOne(id),ProjectEngineerServiceModel.class);
    }

    @Override
    public void addToEngineerById(long id) {
        List<Engineer> engineerList = engineerRepository.findAll();

        if(engineerList.isEmpty()){
            throw new NullPointerException("There are no engineers");
        }

        Optional<Project> projectResult = projectsRepository.findById(id);

        if(projectResult.isEmpty()){
            throw new NullPointerException("Project does not exist");
        }

        Random rnd = new Random();

        int randIndex = rnd.nextInt(engineerList.size()-1);

        List<Engineer> engineersToAdd = new ArrayList<>();

        Project project = projectResult.get();

        for (int i = 0; i < project.getEngineerCapacity() - 1; i++) {
            Engineer engineer = engineerList.get(randIndex);
            engineersToAdd.add(engineer);
        }

        boolean hasProject = false;

        for (Engineer engineer : engineersToAdd) {

            for (Project engineerProject : engineer.getProjects()) {
                if (engineerProject.getName().equals(project.getName())) {
                    hasProject = true;
                    break;
                }
            }

            if (!hasProject) {
                engineer.getProjects().add(project);
                engineer.setRating(calculateRating(engineer));

                engineerRepository.saveAndFlush(engineer);
            }
        }
    }

    @Override
    public void addToCompanyById(long id) {
        List<Company> companyList = companiesRepository.findAll();

        if(companyList.isEmpty()){
            throw new NullPointerException("There are no engineers");
        }

        Optional<Project> projectResult = projectsRepository.findById(id);

        if(projectResult.isEmpty()){
            throw new NullPointerException("Project does not exist");
        }

        Random rnd = new Random();

        int randIndex = rnd.nextInt(companyList.size()-1);

        Project project = projectResult.get();

        Company company = companyList.get(randIndex);

        boolean hasProject = false;

        if (project.getName().equals(company.getProject().getName())) {
        hasProject = true;
        }

        if (!hasProject) {
        company.setProject(project);
        company.setRating(calculateRatingCompany(company));

        companiesRepository.saveAndFlush(company);
        }
    }

    private int calculateRatingCompany(Company company) {
        int rating = company.getRating();
        Project p = company.getProject();

        if(p.getStructure().toString().equals("RESIDENTIAL")){
            rating+=2+p.getComplexity();
        } else if (p.getStructure().toString().equals("INDUSTRIAL")){
            rating+=4+p.getComplexity();
        } else if (p.getStructure().toString().equals("ADMINISTRATIVE")){
            rating+=3+p.getComplexity();
        } else if (p.getStructure().toString().equals("BRIDGE")){
            rating+=5+p.getComplexity();
        } else if (p.getStructure().toString().equals("DAM")){
            rating+=6+p.getComplexity();
        }
        return rating;
    }

    private int calculateRating(Engineer engineer) {
        final int[] rating = {engineer.getExperienceYears() / 10};

        engineer.getProjects().forEach(p->{
            if(p.getStructure().toString().equals("RESIDENTIAL")){
                rating[0]+=2+p.getComplexity();
            } else if (p.getStructure().toString().equals("INDUSTRIAL")){
                rating[0]+=4+p.getComplexity();
            } else if (p.getStructure().toString().equals("ADMINISTRATIVE")){
                rating[0]+=3+p.getComplexity();
            } else if (p.getStructure().toString().equals("BRIDGE")){
                rating[0]+=5+p.getComplexity();
            } else if (p.getStructure().toString().equals("DAM")){
                rating[0]+=6+p.getComplexity();
            }
        });


        return rating[0];
    }

    @Override
    public Project create(ProjectCreateServiceModel projectServiceModel) {

        if(!this.projectValidationService.isProjectValid(projectServiceModel)){
            throw  new RuntimeException("Project is invalid");
        }
        if (this.projectsRepository.existsByName(projectServiceModel.getName())){
            throw new DataIntegrityViolationException("Invalid name");
        }
        Project project = modelMapper.map(projectServiceModel, Project.class);
        project.setEngineerCapacity(calculateCapacity(project));
        projectsRepository.save(project);
        return project;
    }

    private int calculateCapacity(Project project) {
        int capacity = 0;

        if(project.getSquaredMeters()<=10000){
            capacity+=2;
        } else if(project.getSquaredMeters()>10000 && project.getSquaredMeters()<=30000){
            capacity+=3;
        } else if(project.getSquaredMeters()>30000){
            capacity+=4;
        }

        return capacity;
    }

    @Override
    public List<ProjectServiceModel> getAll() {
        return projectsRepository.findAll()
                .stream()
                .map(project -> modelMapper.map(project, ProjectServiceModel.class))
                .collect(Collectors.toList());
    }
}
