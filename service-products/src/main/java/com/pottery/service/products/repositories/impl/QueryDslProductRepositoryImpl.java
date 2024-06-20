package com.pottery.service.products.repositories.impl;

import com.pottery.service.products.dtos.ProductShortDto;
import com.pottery.service.products.dtos.QProductShortDto;
import com.pottery.service.products.entities.Product;
import com.pottery.service.products.entities.QProduct;
import com.pottery.service.products.repositories.QueryDslProductRepository;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class QueryDslProductRepositoryImpl implements QueryDslProductRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ProductShortDto> getRecommendedProducts(Product target) {
        QProduct product = new QProduct("product");
        return queryFactory
                .select(new QProductShortDto(
                        product.id,
                        product.name,
                        product.images,
                        product.catalogPrice,
                        product.discountCatalogPrice
                ))
                .from(product)
                .where(
                        product.category.eq(target.getCategory()),
                        product.id.ne(target.getId())
                )
                .limit(6)
                .fetch();
    }

    @Override
    public Page<ProductShortDto> getProductsByFilter(List<Long> categoryIds,
                                                     List<Long> colorIds,
                                                     List<Long> collectionIds,
                                                     BigDecimal minPrice, BigDecimal maxPrice,
                                                     Boolean isAvailable,
                                                     Pageable pageable) {
        QProduct product = new QProduct("product");

        BooleanExpression[] filters = {
                categoryIdIn(categoryIds),
                colorIdIn(colorIds),
                collectionIdIn(collectionIds),
                minPrice(minPrice),
                maxPrice(maxPrice),
                isAvailable(isAvailable)
        };

        JPAQuery<ProductShortDto> filterQuery = queryFactory
                .select(
                        new QProductShortDto(
                                product.id,
                                product.name,
                                product.images,
                                product.catalogPrice,
                                product.discountCatalogPrice
                        )
                )
                .from(product)
                .where(
                        filters
                );

        if (pageable != null) {
            filterQuery
                    .orderBy(getSortedColumn(pageable))
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize());
        }

        List<ProductShortDto> products = filterQuery.fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(product.count())
                .from(product)
                .where(
                        filters
                );

        pageable = pageable != null ? pageable : Pageable.unpaged();
        return PageableExecutionUtils.getPage(products, pageable,
                countQuery::fetchOne);

    }

    private BooleanExpression categoryIdIn(List<Long> categoryIds) {
        if (categoryIds == null || categoryIds.isEmpty()) {
            return null;
        }

        QProduct product = new QProduct("product");
        return product.category.id.in(categoryIds);
    }

    private BooleanExpression colorIdIn(List<Long> colorIds) {
        if (colorIds == null || colorIds.isEmpty()) {
            return null;
        }

        QProduct product = new QProduct("product");
        return product.properties.any().color.id.in(colorIds);
    }

    private BooleanExpression collectionIdIn(List<Long> collectionIds) {
        if (collectionIds == null || collectionIds.isEmpty()) {
            return null;
        }

        QProduct product = new QProduct("product");
        return product.collections.any().id.in(collectionIds);
    }

    private BooleanExpression minPrice(BigDecimal minPrice) {
        if (minPrice == null) {
            return null;
        }

        QProduct product = new QProduct("product");
        return product.discountCatalogPrice.isNotNull().and(product.discountCatalogPrice.goe(minPrice))
                .or(product.discountCatalogPrice.isNull().and(product.catalogPrice.goe(minPrice)));
    }

    private BooleanExpression maxPrice(BigDecimal maxPrice) {
        if (maxPrice == null) {
            return null;
        }

        QProduct product = new QProduct("product");
        return product.discountCatalogPrice.isNotNull().and(product.discountCatalogPrice.loe(maxPrice))
                .or(product.discountCatalogPrice.isNull().and(product.catalogPrice.loe(maxPrice)));
    }

    private BooleanExpression isAvailable(Boolean isAvailable) {
        if (isAvailable == null) {
            return null;
        }

        QProduct product = new QProduct("product");
        if (isAvailable) {
            return product.properties.any().quantity.gt(0);
        } else {
            return product.properties.any().quantity.eq(0);
        }
    }

    private OrderSpecifier<?>[] getSortedColumn(Pageable pageable) {
        OrderSpecifier[] sorting = pageable.getSort().stream()
                .map(o -> {
                    PathBuilder<Product> orderByExpression = new PathBuilder<>(Product.class, "product");

                    return new OrderSpecifier(o.isAscending() ? com.querydsl.core.types.Order.ASC
                            : com.querydsl.core.types.Order.DESC, orderByExpression.get(o.getProperty()));
                })
                .toArray(OrderSpecifier[]::new);
        return sorting.length != 0 ? sorting : new OrderSpecifier[]{new QProduct("product").id.asc()};
    }

}
