package com.construction.service.services.impl;

import com.construction.data.models.Company;
import com.construction.data.repositories.CompaniesRepository;
import com.construction.errors.CompanyNotFoundException;
import com.construction.service.factories.CompaniesFactory;
import com.construction.service.models.companies.CompanyCreateServiceModel;
import com.construction.service.models.companies.CompanyDetailsServiceModel;
import com.construction.service.services.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompaniesFactory companiesFactory;
    private final CompaniesRepository companiesRepository;
    private final ModelMapper mapper;

    public CompanyServiceImpl(CompaniesFactory companiesFactory, CompaniesRepository companiesRepository, ModelMapper mapper) {
        this.companiesFactory = companiesFactory;
        this.companiesRepository = companiesRepository;
        this.mapper = mapper;
    }


    @Override
    public void create(CompanyCreateServiceModel model) {
        if (this.companiesRepository.existsByName(model.getName())){
            throw new DataIntegrityViolationException("Invalid name !");
        }
        Company company = companiesFactory.create(model.getName(), model.getStructureType(), model.getExperienceYears());
        companiesRepository.saveAndFlush(company);
    }

    @Override
    public CompanyDetailsServiceModel getByName(String name) {
        Optional<Company> engineerResult =companiesRepository.getByNameIgnoreCase(name);

        if(engineerResult.isEmpty()){
            throw new CompanyNotFoundException("Company with such name doesn't exist !");
        }

        Company company = engineerResult.get();

        return mapper.map(company, CompanyDetailsServiceModel.class);
    }

    @Override
    public List<CompanyDetailsServiceModel> getCompaniesByRating() {
        return companiesRepository.findAll().stream()
                .sorted(Comparator.comparingInt(Company::getRating).reversed())
                .map(e->mapper.map(e,CompanyDetailsServiceModel.class))
                .collect(Collectors.toList());
    }
}
