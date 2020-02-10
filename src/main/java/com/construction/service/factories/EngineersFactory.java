package com.construction.service.factories;

import com.construction.data.models.Engineer;
import com.construction.data.models.Gender;

public interface EngineersFactory {

    Engineer create(String name, Gender gender, int experience);
}
