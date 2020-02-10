package com.construction.service.services;

import com.construction.service.models.auth.LoginUserServiceModel;
import com.construction.service.models.auth.RegisterUserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {

    void register(RegisterUserServiceModel model) throws Exception;

//    LoginUserServiceModel login(RegisterUserServiceModel serviceModel) throws Exception;
}
