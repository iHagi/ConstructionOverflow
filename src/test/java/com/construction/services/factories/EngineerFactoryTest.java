package com.construction.services.factories;

import com.construction.data.models.Engineer;
import com.construction.data.models.Gender;
import com.construction.service.factories.EngineersFactory;
import com.construction.services.base.ServiceTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EngineerFactoryTest extends ServiceTestBase {

        @Autowired
        EngineersFactory factory;

        @Test
        void create_withNameAndGenderAndXp_shouldReturnHeroWithDefaultRating() {

            String name = "Ivan";
            Gender gender = Gender.FEMALE;
            int experience = 12;

            Engineer engineer = factory.create(name,gender,experience);

            assertEquals(name, engineer.getName());
            assertEquals(gender, engineer.getGender());
            assertEquals(experience, engineer.getExperienceYears());
            assertEquals(1, engineer.getRating());
        }
}
