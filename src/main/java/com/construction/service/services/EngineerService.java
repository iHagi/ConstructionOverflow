package com.construction.service.services;

import com.construction.data.models.Engineer;
import com.construction.service.models.engineers.EngineerCreateServiceModel;
import com.construction.service.models.engineers.EngineerDetailsServiceModel;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EngineerService {

    void create(EngineerCreateServiceModel model);

    EngineerDetailsServiceModel getByName(String name);

    List<EngineerDetailsServiceModel> getEngineersByRating();
}
