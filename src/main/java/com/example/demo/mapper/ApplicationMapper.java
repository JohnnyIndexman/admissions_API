package com.example.demo.mapper;

import com.example.demo.dto.*;
import com.example.demo.model.AdmissionApplication;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {
    ApplicationMapper INSTANCE = Mappers.getMapper(ApplicationMapper.class);

    AdmissionApplication toEntity(CreateApplicationRequest dto);

    @Mapping(target = "version", ignore = true) // JPA manages version
    void updateFromDto(UpdateApplicationRequest dto, @MappingTarget AdmissionApplication entity);
}
