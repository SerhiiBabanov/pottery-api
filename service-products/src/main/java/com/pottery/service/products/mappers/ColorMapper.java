package com.pottery.service.products.mappers;

import com.pottery.service.products.dtos.ColorDto;
import com.pottery.service.products.entities.Color;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ColorMapper {
    Color toEntity(ColorDto colorDto);

    ColorDto toDto(Color color);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Color partialUpdate(ColorDto colorDto, @MappingTarget Color color);
}
