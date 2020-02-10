package com.construction.web.view.controllers;

import com.construction.service.models.auth.LoginUserServiceModel;
import com.construction.service.services.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    private final UsersService usersService;

    public HomeController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/")
    public String getIndex() {
        return "home/index.html";
    }

    @GetMapping("/home")
    public String getHome(Principal principal){
        LoginUserServiceModel serviceModel =usersService.getUserByUsername(principal.getName());

        if(serviceModel.getAuthorities().size()==1){
            return "home/home-guest.html";
        }

        return "home/home.html";
    }


}
