package com.construction.service.services.impl;

import com.construction.data.models.Role;
import com.construction.data.models.User;
import com.construction.data.repositories.RoleRepository;
import com.construction.data.repositories.UsersRepository;
import com.construction.service.models.auth.LoginUserServiceModel;
import com.construction.service.models.auth.RegisterUserServiceModel;
import com.construction.service.services.AuthService;
import com.construction.service.services.validation.AuthValidationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthValidationService authValidationService;
    private final RoleRepository roleRepository;
    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(AuthValidationService authValidationService,
                           RoleRepository roleRepository, UsersRepository usersRepository,
                           ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder) {
        this.authValidationService = authValidationService;
        this.roleRepository = roleRepository;
        this.usersRepository = usersRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(RegisterUserServiceModel model) throws Exception {
        if(!authValidationService.isValid(model)){
            throw new Exception("Invalid data provided !");
        }

        User user = modelMapper.map(model, User.class);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        user.setRoles(getRolesForRegistration());
        usersRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        return this.usersRepository.findByUsername(s)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found."));
    }

    private Set<Role> getRolesForRegistration() {
        Set<Role> roles = new HashSet<>();

        if(this.usersRepository.count() == 0) {
            roles.add(this.roleRepository.findByName("ADMIN"));
            roles.add(this.roleRepository.findByName("MODERATOR"));
            roles.add(this.roleRepository.findByName("USER"));
        } else {
            roles.add(this.roleRepository.findByName("USER"));
        }

        return roles;
    }

}
