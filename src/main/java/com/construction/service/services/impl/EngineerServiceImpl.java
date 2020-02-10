package com.construction.service.services.impl;

import com.construction.data.models.Engineer;
import com.construction.data.repositories.EngineersRepository;
import com.construction.errors.EngineerNotFoundException;
import com.construction.service.factories.EngineersFactory;
import com.construction.service.models.engineers.EngineerCreateServiceModel;
import com.construction.service.models.engineers.EngineerDetailsServiceModel;
import com.construction.service.services.EngineerService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EngineerServiceImpl implements EngineerService {
    private final EngineersFactory engineersFactory;
    private final EngineersRepository engineerRepository;
    private final ModelMapper mapper;

    public EngineerServiceImpl(EngineersFactory engineersFactory, EngineersRepository engineerRepository, ModelMapper mapper) {
        this.engineersFactory = engineersFactory;
        this.engineerRepository = engineerRepository;
        this.mapper = mapper;
    }

    @Override
    public void create(EngineerCreateServiceModel model) {
        if (this.engineerRepository.existsByName(model.getName())){
            throw new DataIntegrityViolationException("Invalid name");
        }
        Engineer engineer = engineersFactory.create(model.getName(), model.getGender(), model.getExperienceYears());
        engineerRepository.saveAndFlush(engineer);

    }

    @Override
    public EngineerDetailsServiceModel getByName(String name) {
         Optional<Engineer> engineerResult =engineerRepository.getByNameIgnoreCase(name);

         if(engineerResult.isEmpty()){
             throw new EngineerNotFoundException("Engineer with such name doesn't exist !");
         }

         Engineer engineer = engineerResult.get();


        return mapper.map(engineer, EngineerDetailsServiceModel.class);
    }

    @Override
    public List<EngineerDetailsServiceModel> getEngineersByRating() {
        return engineerRepository.findAll().stream()
                .sorted(Comparator.comparingInt(Engineer::getRating).reversed())
                .map(e->mapper.map(e,EngineerDetailsServiceModel.class))
                .collect(Collectors.toList());
    }

}
