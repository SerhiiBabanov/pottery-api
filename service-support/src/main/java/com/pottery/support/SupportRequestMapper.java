package com.pottery.support;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SupportRequestMapper {
    SupportRequest toEntity(SupportRequestDto supportRequestDto);

    SupportRequestDto toDto(SupportRequest supportRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    SupportRequest partialUpdate(SupportRequestDto supportRequestDto, @MappingTarget SupportRequest supportRequest);
}
