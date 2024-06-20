package com.pottery.service.products.mappers;

import com.pottery.service.products.dtos.ProductDto;
import com.pottery.service.products.entities.Product;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
    uses = {PropertiesMapper.class, CollectionMapper.class, CategoryMapper.class})
public interface ProductMapper {
    Product toEntity(ProductDto productDto);

    @AfterMapping
    default void linkProperties(@MappingTarget Product product) {
        product.getProperties().forEach(property -> property.setProduct(product));
    }

    ProductDto toDto(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product partialUpdate(ProductDto productDto, @MappingTarget Product product);
}
