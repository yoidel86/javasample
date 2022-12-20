package com.musalatest.dronetest.delegate;

import com.musalatest.dronetest.ChecksApiDelegate;
import com.musalatest.dronetest.mapper.ChecksMapper;
import com.musalatest.dronetest.model.Checks;
import com.musalatest.dronetest.model.ChecksPageDto;
import com.musalatest.dronetest.model.PageableDto;
import com.musalatest.dronetest.repository.ChecksRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ChecksApiDelegateService implements ChecksApiDelegate{
    final ChecksRepository checksRepository;
    final ChecksMapper checksMapper;


    public ChecksApiDelegateService(ChecksRepository checksRepository, ChecksMapper checksMapper) {
        this.checksRepository = checksRepository;
        this.checksMapper = checksMapper;
    }

    public ResponseEntity<ChecksPageDto> listChecks(PageableDto pageable) {
        List<Checks> checksList;
        ChecksPageDto result = new ChecksPageDto();
        if(Objects.isNull(pageable)){
            checksList = checksRepository.findAll();
            result.setPage(0);
            result.setContent(checksMapper.toDtos(checksList));
            result.setElements(checksList.size());
            result.setPages(1);
            result.setSize(checksList.size());
        }else{
            Page<Checks> checksPage = checksRepository.findAll(PageRequest.of(pageable.getPage(), pageable.getSize()));
            result.setSize(checksPage.getSize());
            result.setContent(checksMapper.toDtos(checksPage.getContent()));
            result.setElements((int) checksPage.getTotalElements());
            result.setPages(checksPage.getTotalPages());
            result.setPage(checksPage.getNumber());
        }

        return ResponseEntity.ok(result);
    }



}
