package com.construction.services.factories;

import com.construction.data.models.Company;
import com.construction.data.models.Structure;
import com.construction.service.factories.CompaniesFactory;
import com.construction.service.factories.EngineersFactory;
import com.construction.services.base.ServiceTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompanyFactoryTest extends ServiceTestBase {


    @Autowired
    CompaniesFactory factory;

    @Test
    void create_withNameAndGenderAndXp_shouldReturnHeroWithDefaultRating() {
        String name = "Ivanovi";
        Structure structureType = Structure.RESIDENTIAL;
        int experience = 12;

        Company company = factory.create(name, structureType, experience);

        assertEquals(name, company.getName());
        assertEquals(structureType, company.getStructureType());
        assertEquals(experience, company.getExperienceYears());
        assertEquals(3, company.getRating());
    }

    }
