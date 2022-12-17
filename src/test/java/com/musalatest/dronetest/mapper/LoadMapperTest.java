package com.musalatest.dronetest.mapper;

import com.musalatest.dronetest.TestFactory;
import com.musalatest.dronetest.model.Load;
import com.musalatest.dronetest.model.LoadDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LoadMapperTest {

    //@Todo implement Load Map tests

    @Spy
    private LoadMapper loadMapper = Mappers.getMapper(LoadMapper.class);

    @Test
    void toDto() {
        Load load = TestFactory.getLoad();
        LoadDto dto = loadMapper.toDto(load);

        assertTrue(dto.getId()==load.getId());
        assertTrue(dto.getDroneId()==load.getDrone().getId());
        assertTrue(dto.getMedicationsId().get(0)==load.getMedications().get(0).getId());
    }


    @Test
    void toDtos() {
        List<Load> loadList = new ArrayList<>();
        loadList.add(TestFactory.getLoad(1));
        loadList.add(TestFactory.getLoad(2));
        loadList.add(TestFactory.getLoad(3));
        List<LoadDto> loadDtos = loadMapper.toDtos(loadList);

        assertTrue(loadDtos.size()==loadList.size());
        assertTrue(loadDtos.get(0).getId()==loadList.get(0).getId());
        assertTrue(loadDtos.get(2).getId()==loadList.get(2).getId());
        assertTrue(loadDtos.get(2).getMedicationsId().size()==loadList.get(2).getMedications().size());
    }

    @Test
    void droneDtoToDrone() {
    }



}
