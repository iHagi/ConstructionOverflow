package com.construction.web.view.controllers;

import com.construction.errors.EngineerNotFoundException;
import com.construction.service.models.companies.CompanyCreateServiceModel;
import com.construction.service.models.companies.CompanyDetailsServiceModel;
import com.construction.service.models.engineers.EngineerCreateServiceModel;
import com.construction.service.models.engineers.EngineerDetailsServiceModel;
import com.construction.service.services.CompanyService;
import com.construction.service.services.EngineerService;
import com.construction.web.view.models.CompanyCreateModel;
import com.construction.web.view.models.EngineerCreateModel;
import com.construction.web.view.models.EngineerDetailsViewModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@AllArgsConstructor
public class ModeratorController {

    private final EngineerService engineerService;
    private final CompanyService companyService;
    private final ModelMapper mapper;

    @GetMapping("/create_engineer")
    public String getCreateEngineer(){
        System.out.println();
        return "engineers/create-engineer.html";
    }

    @PostMapping("/create_engineer")
    @PreAuthorize("isFullyAuthenticated()")
    public String createEngineer(@ModelAttribute EngineerCreateModel engineer){

        EngineerCreateServiceModel serviceModel = mapper.map(engineer, EngineerCreateServiceModel.class);
        try {
            engineerService.create(serviceModel);
            return "redirect:/create_engineer";
        } catch (Exception ex) {
            return "redirect:/home";
        }
    }

    @GetMapping("/all_engineers")
    public ModelAndView getAllEngineers(@ModelAttribute ModelAndView modelAndView){
        List<EngineerDetailsServiceModel> engineersList = engineerService.getEngineersByRating();
        modelAndView.addObject("engineers", engineersList);
        modelAndView.addObject("title", "Eng.");
        modelAndView.setViewName("engineers/engineer-all");
        return modelAndView;
    }

    @GetMapping("/details_engineer/{name}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getEngineerDetails(@PathVariable String name, ModelAndView modelAndView) {
        EngineerDetailsServiceModel serviceModel = engineerService.getByName(name);
        EngineerDetailsViewModel viewModel = mapper.map(serviceModel, EngineerDetailsViewModel.class);
        modelAndView.addObject("engineer", viewModel);
        modelAndView.setViewName("engineers/engineer-details");
        return modelAndView;
    }

    @ExceptionHandler(EngineerNotFoundException.class)
    public ModelAndView handleException(EngineerNotFoundException exception ){
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", exception.getMessage());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        modelAndView.setViewName("error");
        return modelAndView;
    }

    @GetMapping("/create_company")
    public String getCreateCompany(){
        return "companies/create-company.html";
    }

    @PostMapping("/create_company")
    @PreAuthorize("isFullyAuthenticated()")
    public String createCompany(@ModelAttribute CompanyCreateModel company){

        CompanyCreateServiceModel serviceModel = mapper.map(company, CompanyCreateServiceModel.class);
        try {

            companyService.create(serviceModel);

            return "redirect:/create_company";
        } catch (Exception ex) {
            return "redirect:/home";
        }
    }

    @GetMapping("/all_companies")
    public ModelAndView getAllCompanies(@ModelAttribute ModelAndView modelAndView){

        List<CompanyDetailsServiceModel> companyList = companyService.getCompaniesByRating();
        modelAndView.addObject("companies", companyList);
        modelAndView.addObject("title", "Inc.");
        modelAndView.setViewName("companies/company-all");
        return modelAndView;
    }


}
