package com.musalatest.dronetest.delegate;

import com.musalatest.dronetest.LoadsApiDelegate;
import com.musalatest.dronetest.mapper.LoadMapper;
import com.musalatest.dronetest.model.Load;
import com.musalatest.dronetest.model.LoadDto;
import com.musalatest.dronetest.repository.LoadRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class LoadsApiDelegateService implements LoadsApiDelegate {
    final LoadRepository loadRepository;
    final LoadMapper loadMapper;

    public LoadsApiDelegateService(LoadRepository loadRepository, LoadMapper loadMapper) {
        this.loadRepository = loadRepository;
        this.loadMapper = loadMapper;
    }

    public ResponseEntity<List<LoadDto>> listLoads() {
        List<Load> loads =  loadRepository.findAll();
        List<LoadDto> result = loadMapper.toDtos(loads);
        return ResponseEntity.ok(result);
    }
}
