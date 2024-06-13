package com.pottery.service.products.mappers;

import com.pottery.service.products.dtos.CollectionDto;
import com.pottery.service.products.entities.Collection;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CollectionMapper {
    Collection toEntity(CollectionDto collectionDto);

    CollectionDto toDto(Collection collection);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Collection partialUpdate(CollectionDto collectionDto, @MappingTarget Collection collection);
}
