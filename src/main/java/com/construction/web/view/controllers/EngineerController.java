package com.construction.web.view.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/engineers")
public class EngineerController {

    @GetMapping("/details/{id}")
    public ModelAndView detailsEng(@PathVariable(name = "id") long id, ModelAndView modelAndView){
        System.out.println();

        return modelAndView;
    }
}
