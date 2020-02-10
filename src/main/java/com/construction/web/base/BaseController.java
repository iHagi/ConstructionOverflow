package com.construction.web.base;

import com.construction.data.models.Role;
import com.construction.service.models.auth.LoginUserServiceModel;

import javax.servlet.http.HttpSession;
import java.util.Set;

public class BaseController {
    protected String getUsername(HttpSession session){
        return ((LoginUserServiceModel)session.getAttribute("user")).getUsername();
    }

    protected Set<Role> getAuthorities(HttpSession session){
        return ((LoginUserServiceModel)session.getAttribute("user")).getAuthorities();
    }
}
