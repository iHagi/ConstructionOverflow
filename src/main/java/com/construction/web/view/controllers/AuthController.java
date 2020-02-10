package com.construction.web.view.controllers;

import com.construction.service.models.auth.LoginUserServiceModel;
import com.construction.service.models.auth.RegisterUserServiceModel;
import com.construction.service.services.AuthService;
import com.construction.web.view.models.RegisterUserModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class AuthController {

    private final AuthService authService;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthController(AuthService authService,
                          ModelMapper modelMapper) {
        this.authService = authService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    @PreAuthorize("isAnonymous()")
    public String getLoginForm(){
        return "auth/login.html";
    }

    @GetMapping("/register")
    @PreAuthorize("isAnonymous()")
    public String getRegisterForm(){
        return "auth/register.html";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute RegisterUserModel model)  {
        RegisterUserServiceModel serviceModel = modelMapper.map(model, RegisterUserServiceModel.class);
        try {
            authService.register(serviceModel);
            return "redirect:/login";
        } catch (Exception e) {
            return "redirect:/register";
        }
    }

//    @PostMapping("/login")
//    public String login(@ModelAttribute RegisterUserModel model, HttpSession session){
//        RegisterUserServiceModel serviceModel = modelMapper.map(model, RegisterUserServiceModel.class);
//        try {
//            LoginUserServiceModel loginUserServiceModel = authService.login(serviceModel);
//            authService.login(serviceModel);
//            session.setAttribute("user", loginUserServiceModel);
//            return "redirect:/home";
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "redirect:/users/login";
//        }
//    }

}
