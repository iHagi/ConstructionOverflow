package com.construction.service.models.auth;

import com.construction.data.models.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class LoginUserServiceModel {
    private String username;
    private Set<Role> authorities;

    public LoginUserServiceModel(String username, Set<Role> authorities) {
        this.username = username;
        this.authorities = authorities;
    }
}
