package com.musalatest.dronetest.mapper;

import com.musalatest.dronetest.model.ChecksDto;
import com.musalatest.dronetest.model.Checks;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChecksMapper {


    ChecksDto toDto(final Checks checks);

    List<ChecksDto> toDtos(final List<Checks> checkss);

    @InheritInverseConfiguration
    Checks checksDtoToChecks( final ChecksDto checksDto);

}

