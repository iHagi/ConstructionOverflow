package com.construction.service.factories;

import com.construction.config.annotations.Factory;
import com.construction.data.models.Company;
import com.construction.data.models.Structure;

@Factory
public class CompaniesFactoryImpl implements CompaniesFactory {


    @Override
    public Company create(String name, Structure structureType, int experience) {
        Company company = new Company();

        company.setName(name);
        company.setStructureType(structureType);
        company.setExperienceYears(experience);
        company.setRating(calculateRating(company));

        return company;
    }

    private int calculateRating(Company company) {
        int rating = company.getExperienceYears() / 10;

            if(company.getStructureType().toString().equals("RESIDENTIAL")){
                rating+=2;
            } else if (company.getStructureType().toString().equals("INDUSTRIAL")){
                rating+=4;
            } else if (company.getStructureType().toString().equals("ADMINISTRATIVE")){
                rating+=3;
            } else if (company.getStructureType().toString().equals("BRIDGE")){
                rating+=5;
            } else if (company.getStructureType().toString().equals("DAM")){
                rating+=6;
            }

        return rating;
    }
}
