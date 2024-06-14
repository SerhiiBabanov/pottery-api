package com.pottery.service.products.services;

import com.pottery.service.products.dtos.ProductShortDto;
import com.pottery.service.products.entities.Product;
import com.pottery.service.products.exceptions.AppException;
import com.pottery.service.products.repositories.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Testcontainers
@ActiveProfiles("test-containers-flyway")
class ProductServiceTest {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @Nested
    @DisplayName("getByFilters method")
    class getByFilters {

        @Test
        @DisplayName("When filters are null and pageable is null must return all products")
        void getByFilters_when_all_filters_and_pageable_are_NULL() {
            //given
            //when
            Page<ProductShortDto> products = productService.getByFilters(null, null, null,
                    null, null, null, null);
            //then
            assertNotNull(products, "Products should not be null");
            assertEquals(15, products.getTotalElements(), "Total elements should be 15");
        }

        @Test
        @DisplayName("When filters are null and page size = 5 and page = 1 must return correct page")
        void getByFilters_when_all_filters_are_NULL_and_page_size_5() {
            //given
            Pageable pageable = Pageable.ofSize(5).withPage(1);
            //when
            Page<ProductShortDto> products = productService.getByFilters(null, null, null,
                    null, null, null, pageable);
            //then
            assertNotNull(products, "Products should not be null");
            assertEquals(15, products.getTotalElements(), "Total elements should be 15");
            assertEquals(5, products.getSize(), "Page size should be 5");
            assertEquals(3, products.getTotalPages(), "Total pages should be 3");
            assertEquals(1, products.getNumber(), "Current page should be 1");
        }

        @Test
        @DisplayName("When filters are null and page size = 15 and sort = catalogPrice,DESC must return sorted page")
        void getByFilters_when_all_filters_are_NULL_and_page_size_15_and_sort_by_defaultPrice_DESC() {
            //given
            Pageable pageable = PageRequest.of(0, 15, Sort.by("catalogPrice").descending());
            //when
            Page<ProductShortDto> products = productService.getByFilters(null, null, null,
                    null, null, null, pageable);
            //then
            assertNotNull(products, "Products should not be null");
            assertEquals(15, products.getTotalElements(), "Total elements should be 15");
            assertEquals(15, products.getSize(), "Page size should be 15");
            assertEquals(1, products.getTotalPages(), "Total pages should be 1");
            assertEquals(0, products.getNumber(), "Current page should be 0");
            assertTrue(products.getContent().get(0).catalogPrice().compareTo(BigDecimal.valueOf(200)) == 0,
                    "First product should have catalog price 200 - maximum in test db");
            assertTrue(products.getContent().get(14).catalogPrice().compareTo(BigDecimal.valueOf(60)) == 0,
                    "Last product should have catalog price 60 - minimum in test db");
        }

        @Test
        @Transactional
        @DisplayName("When categoryIds content 1L must return 5 products")
        void getByFilters_when_categoryIds_present() {
            //given
            List<Long> categoryIds = List.of(1L);
            //when
            Page<ProductShortDto> products = productService.getByFilters(categoryIds, null, null,
                    null, null, null, null);
            //then
            assertNotNull(products, "Products should not be null");
            assertEquals(5, products.getTotalElements(), "Total elements should be 5");
            assertTrue(products.getContent().stream()
                            .allMatch(p -> productRepository.getReferenceById(p.id()).getCategory().getId().equals(1L)),
                    "All products must content collection id: 1L");
        }

        @Test
        @Transactional
        @DisplayName("When colorIds content 1L must return 5 products")
        void getByFilters_when_colorsIds_present() {
            //given
            List<Long> colorIds = List.of(1L);
            //when
            Page<ProductShortDto> products = productService.getByFilters(null, colorIds, null,
                    null, null, null, null);
            //then
            assertNotNull(products, "Products should not be null");
            assertEquals(5, products.getTotalElements(), "Total elements should be 5");
            assertTrue(products.getContent().stream()
                            .allMatch(p -> productRepository.getReferenceById(p.id()).getProperties().stream()
                                    .anyMatch(pr -> (pr.getColor().getId().equals(1L)))
                            ),
                    "All products must content color id: 1L");
        }

        @Test
        @Transactional
        @DisplayName("When collectionIds content 1L must return 5 products")
        void getByFilters_when_collectionIds_present() {
            //given
            List<Long> collectionIds = List.of(1L);
            //when
            Page<ProductShortDto> products = productService.getByFilters(null, null, collectionIds,
                    null, null, null, null);
            //then
            assertNotNull(products, "Products should not be null");
            assertEquals(5, products.getTotalElements(), "Total elements should be 5");
            assertTrue(products.getContent().stream()
                            .allMatch(p -> productRepository.getReferenceById(p.id()).getCollections().stream()
                                    .anyMatch(c -> c.getId().equals(1L))
                            ),
                    "All products must content collection id: 1L");
        }

        @Test
        @Transactional
        @DisplayName("When minPrice content 110 must return 10 products")
        void getByFilters_when_minPrice_present() {
            //given
            BigDecimal minPrice = new BigDecimal(110);
            //when
            Page<ProductShortDto> products = productService.getByFilters(null, null, null,
                    minPrice, null, null, null);
            //then
            assertNotNull(products, "Products should not be null");
            assertEquals(10, products.getTotalElements(), "Total elements should be 5");
            assertTrue(products.getContent().stream()
                            .allMatch(p -> productRepository.getReferenceById(p.id()).getProperties().stream()
                                    .allMatch(pr -> (pr.getDiscountPrice() != null
                                            ? pr.getDiscountPrice().compareTo(minPrice) >= 0
                                            : pr.getDefaultPrice().compareTo(minPrice) >= 0)
                                    )
                            ),
                    "All prices must be greater or equal to 110");
        }

        @Test
        @Transactional
        @DisplayName("When maxPrice content 110 must return 6 products")
        void getByFilters_when_maxPrice_present() {
            //given
            BigDecimal maxPrice = new BigDecimal(110);
            //when
            Page<ProductShortDto> products = productService.getByFilters(null, null, null,
                    null, maxPrice, null, null);
            //then
            assertNotNull(products, "Products should not be null");
            assertEquals(6, products.getTotalElements(), "Total elements should be 5");
            assertTrue(products.getContent().stream()
                            .allMatch(p -> productRepository.getReferenceById(p.id()).getProperties().stream()
                                    .allMatch(pr -> (pr.getDiscountPrice() != null
                                            ? pr.getDiscountPrice().compareTo(maxPrice) <= 0
                                            : pr.getDefaultPrice().compareTo(maxPrice) <= 0)
                                    )
                            ),
                    "All prices must be less or equal to 110");
        }

        @Test
        @Transactional
        @DisplayName("When isAvailable true must return 14 products")
        void getByFilters_when_isAvailable_true_present() {
            //given
            Boolean isAvailable = true;
            //when
            Page<ProductShortDto> products = productService.getByFilters(null, null, null,
                    null, null, isAvailable, null);
            //then
            assertNotNull(products, "Products should not be null");
            assertEquals(14, products.getTotalElements(), "Total elements should be 14");
            assertTrue(products.getContent().stream()
                            .allMatch(p -> productRepository.getReferenceById(p.id()).getProperties().stream()
                                    .anyMatch(pr -> pr.getQuantity() > 0)
                            ),
                    "At least one product quantity should be greater than 0");

        }

        @Test
        @Transactional
        @DisplayName("When isAvailable false must return 1 product")
        void getByFilters_when_isAvailable_false_present() {
            //given
            Boolean isAvailable = false;
            //when
            Page<ProductShortDto> products = productService.getByFilters(null, null, null,
                    null, null, isAvailable, null);
            //then
            assertNotNull(products, "Products should not be null");
            assertEquals(1, products.getTotalElements(), "Total elements should be 14");
            assertTrue(products.getContent().stream()
                            .allMatch(p -> productRepository.getReferenceById(p.id()).getProperties().stream()
                                    .allMatch(pr -> pr.getQuantity() == 0)
                            ),
                    "All product quantities should be 0");
        }

    }

    @Nested
    @DisplayName("getById method")
    class getById {

        @Test
        @Transactional
        @DisplayName("When id 1L must return product with id 1L")
        void getById_when_id_1L() {
            //given
            Long id = 1L;
            //when
            Product product = productService.getById(id);
            //then
            assertNotNull(product, "Product should not be null");
            assertEquals(id, product.getId(), "Product id should be 1L");
        }

        @Test
        @Transactional
        @DisplayName("When id 100L must return AppException with message 'Not found product with id: 100' and status 404")
        void getById_when_id_100L() {
            //given
            Long id = 100L;
            //when
            try {
                productService.getById(id);
                fail("Exception AppException with status 404 did not throw!");
            } catch (AppException e) {
                //then
                assertEquals("Not found product with id: 100", e.getMessage());
                assertEquals(404, e.getStatus().value(), "Status should be 404");
            }
        }
    }

    @Test
    @DisplayName("When product id 1L must return recommended products with same categoryId (limit 6)")
    @Transactional
    void getRecommendedProducts() {
        //given
        Long productId = 1L;
        Product product = productService.getById(productId);
        //when
        List<ProductShortDto> recommendedProducts = productService.getRecommendedProducts(productId);
        //then
        assertNotNull(recommendedProducts, "Recommended products should not be null");
        assertTrue(recommendedProducts.size() < 6, "Recommended products size should be less than 6");
        assertTrue(recommendedProducts.stream()
                        .allMatch(p -> productRepository.getReferenceById(p.id())
                                .getCategory().getId().equals(product.getCategory().getId())
                        ),
                "All recommended products must content category id: 1L");

    }
}
