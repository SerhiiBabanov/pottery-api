package com.pottery.service.products.mappers;

import com.pottery.service.products.dtos.PropertiesDto;
import com.pottery.service.products.entities.Properties;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PropertiesMapper {
    Properties toEntity(PropertiesDto propertiesDto);

    PropertiesDto toDto(Properties properties);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Properties partialUpdate(PropertiesDto propertiesDto, @MappingTarget Properties properties);
}
