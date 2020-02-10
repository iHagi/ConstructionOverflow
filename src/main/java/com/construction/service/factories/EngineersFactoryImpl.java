package com.construction.service.factories;

import com.construction.config.annotations.Factory;
import com.construction.data.models.Engineer;
import com.construction.data.models.Gender;

@Factory
public class EngineersFactoryImpl implements EngineersFactory {

    @Override
    public Engineer create(String name, Gender gender, int experience) {
        Engineer engineer = new Engineer();

        engineer.setName(name);
        engineer.setGender(gender);
        engineer.setExperienceYears(experience);
        engineer.setRating(engineer.getExperienceYears()/10);

        return engineer;
    }
}
