package com.construction.service.services;

import com.construction.data.models.Company;
import com.construction.data.models.Engineer;
import com.construction.service.models.companies.CompanyCreateServiceModel;
import com.construction.service.models.companies.CompanyDetailsServiceModel;

import java.util.List;

public interface CompanyService {

    void create(CompanyCreateServiceModel model);

    CompanyDetailsServiceModel getByName(String name);

    List<CompanyDetailsServiceModel> getCompaniesByRating();
}
