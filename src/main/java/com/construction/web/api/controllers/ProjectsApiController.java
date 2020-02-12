package com.construction.web.api.controllers;

import com.construction.service.models.projects.ProjectCreateServiceModel;
import com.construction.service.services.ProjectsService;
import com.construction.service.services.UsersService;
import com.construction.web.api.models.ProjectCreateRequestModel;
import com.construction.web.api.models.ProjectResponseModel;
import com.construction.web.base.BaseController;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class ProjectsApiController  {
    private final ProjectsService projectsService;
    private final UsersService usersService;
    private final ModelMapper mapper;

    @GetMapping(value = "/api/projects")
    public ResponseEntity<List<ProjectResponseModel>> getProjects(Principal principal) throws InterruptedException {
        Thread.sleep(2500);
        String username = principal.getName();
        List<ProjectResponseModel> result = usersService.getProjectsForUser(username)
                .stream()
                .map(project -> mapper.map(project, ProjectResponseModel.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/api/projects-all")
    public List<ProjectResponseModel> getProjects() {

        return  projectsService.getAll()
                .stream()
                .map(project -> mapper.map(project, ProjectResponseModel.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/api/projects/add-to-engineer/{id}")
    public void auctionizeProject(@PathVariable long id, HttpServletResponse response) throws IOException {
        System.out.println(id);

        projectsService.addToEngineerById(id);

        response.sendRedirect("/projects/details_engineer/"+id);
    }

    @PostMapping("/api/projects/add-to-company/{id}")
    public void auctionizeProjectCompany(@PathVariable long id, HttpServletResponse response) throws IOException {
        System.out.println();
        projectsService.addToCompanyById(id);

        response.sendRedirect("/projects/details_company/"+id);
    }


    @PostMapping("/api/projects")
    public ResponseEntity<Void> create(ProjectCreateRequestModel requestModel, Principal principal) {
        String username = principal.getName();
        ProjectCreateServiceModel serviceModel = mapper.map(requestModel, ProjectCreateServiceModel.class);
        usersService.createProjectForUser(username,serviceModel);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/projects/auctioneer");
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }


}
