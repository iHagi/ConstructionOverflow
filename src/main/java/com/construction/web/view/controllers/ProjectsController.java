package com.construction.web.view.controllers;

import com.construction.service.models.projects.ProjectEngineerServiceModel;
import com.construction.service.services.ProjectsService;
import com.construction.web.view.models.ProjectEngineerDetailsModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/projects")
@AllArgsConstructor
public class ProjectsController {

    private final ProjectsService projectsService;
    private final ModelMapper mapper;

    @GetMapping("/details_engineer/{id}")
    public ModelAndView getEngineerDetails(@PathVariable(name = "id") Long id, ModelAndView modelAndView) {
        ProjectEngineerServiceModel serviceModel = projectsService.getById(id);

        ProjectEngineerDetailsModel viewModel = mapper.map(serviceModel, ProjectEngineerDetailsModel.class);
        modelAndView.addObject("project", viewModel);
        modelAndView.setViewName("projects/project-details-engineer");
        return modelAndView;
    }

    @GetMapping("/details_company/{id}")
    public ModelAndView getCompanyDetails(@PathVariable("id") Long id, ModelAndView modelAndView) {
        ProjectEngineerServiceModel serviceModel = projectsService.getById(id);

        ProjectEngineerDetailsModel viewModel = mapper.map(serviceModel, ProjectEngineerDetailsModel.class);
        modelAndView.addObject("project", viewModel);
        modelAndView.setViewName("projects/project-details-company");
        return modelAndView;
    }

    @GetMapping("/auctioneer")
    public String getMerchant(){
        return "projects/auctioneer.html";
    }

    @GetMapping("/create")
    public String getCreateItemForm(){
        return "projects/create-project.html";
    }

}

