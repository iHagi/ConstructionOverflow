package com.construction.services.service;

import com.construction.base.BaseTest;
import com.construction.data.repositories.EngineersRepository;
import com.construction.errors.EngineerNotFoundException;
import com.construction.service.services.EngineerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class EngineerServiceTest extends BaseTest {

    @MockBean
    EngineersRepository engineersRepository;

    @Autowired
    EngineerService service;

    @Test
    void getByName_whenEngineerDoesNotExist(){
        String engineerName = "Penyo";

        Mockito.when(engineersRepository.getByNameIgnoreCase(engineerName))
                .thenReturn(Optional.empty());

        assertThrows(
                EngineerNotFoundException.class,
                () -> service.getByName(engineerName));

    }

}
