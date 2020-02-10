package com.construction.service.factories;

import com.construction.data.models.Company;
import com.construction.data.models.Structure;

public interface CompaniesFactory {

    Company create(String name, Structure structureType, int experience);
}
