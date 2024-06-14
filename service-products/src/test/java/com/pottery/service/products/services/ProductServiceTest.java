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
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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
        @DisplayName("Get when filters are null must return all products")
        void getByFilters_when_all_filters_are_NULL() {
            //given
            Pageable pageable = Pageable.unpaged();
            //when
            Page<ProductShortDto> products = productService.getByFilters(null, null, null,
                    null, null, null, pageable);
            //then
            assertNotNull(products);
            assertEquals(15, products.getTotalElements(), "Total elements should be 15");
        }

        @Test
        @Transactional
        @DisplayName("Get when categoryIds content 1L must return 5 products")
        void getByFilters_when_categoryIds_present() {
            //given
            List<Long> categoryIds = List.of(1L);
            Pageable pageable = Pageable.unpaged();
            //when
            Page<ProductShortDto> products = productService.getByFilters(categoryIds, null, null,
                    null, null, null, pageable);
            //then
            assertNotNull(products);
            assertEquals(5, products.getTotalElements(), "Total elements should be 5");
            assertTrue(products.getContent().stream()
                            .allMatch(p -> productRepository.getReferenceById(p.id()).getCategory().getId().equals(1L)),
                    "All products must content collection id: 1L");
        }

        @Test
        @Transactional
        @DisplayName("Get when colorIds content 1L must return 5 products")
        void getByFilters_when_colorsIds_present() {
            //given
            List<Long> colorIds = List.of(1L);
            Pageable pageable = Pageable.unpaged();
            //when
            Page<ProductShortDto> products = productService.getByFilters(null, colorIds, null,
                    null, null, null, pageable);
            //then
            assertNotNull(products);
            assertEquals(5, products.getTotalElements(), "Total elements should be 5");
            assertTrue(products.getContent().stream()
                            .allMatch(p -> productRepository.getReferenceById(p.id()).getProperties().stream()
                                    .anyMatch(pr -> (pr.getColor().getId().equals(1L)))
                            ),
                    "All products must content color id: 1L");
        }

        @Test
        @Transactional
        @DisplayName("Get when collectionIds content 1L must return 5 products")
        void getByFilters_when_collectionIds_present() {
            //given
            List<Long> collectionIds = List.of(1L);
            Pageable pageable = Pageable.unpaged();
            //when
            Page<ProductShortDto> products = productService.getByFilters(null, null, collectionIds,
                    null, null, null, pageable);
            //then
            assertNotNull(products);
            assertEquals(5, products.getTotalElements(), "Total elements should be 5");
            assertTrue(products.getContent().stream()
                            .allMatch(p -> productRepository.getReferenceById(p.id()).getCollections().stream()
                                    .anyMatch(c -> c.getId().equals(1L))
                            ),
                    "All products must content collection id: 1L");
        }

        @Test
        @Transactional
        @DisplayName("Get when minPrice content 110 must return 10 products")
        void getByFilters_when_minPrice_present() {
            //given
            BigDecimal minPrice = new BigDecimal(110);
            Pageable pageable = Pageable.unpaged();
            //when
            Page<ProductShortDto> products = productService.getByFilters(null, null, null,
                    minPrice, null, null, pageable);
            Optional<Product> product = productRepository.findById(products.getContent().get(0).id());
            //then
            assertNotNull(products);
            assertEquals(10, products.getTotalElements(), "Total elements should be 5");
            assertTrue(product.isPresent());
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
        @DisplayName("Get when maxPrice content 110 must return 6 products")
        void getByFilters_when_maxPrice_present() {
            //given
            BigDecimal maxPrice = new BigDecimal(110);
            Pageable pageable = Pageable.unpaged();
            //when
            Page<ProductShortDto> products = productService.getByFilters(null, null, null,
                    null, maxPrice, null, pageable);
            //then
            assertNotNull(products);
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
        @DisplayName("Get when isAvailable true must return 14 products")
        void getByFilters_when_isAvailable_true_present() {
            //given
            Boolean isAvailable = true;
            Pageable pageable = Pageable.unpaged();
            //when
            Page<ProductShortDto> products = productService.getByFilters(null, null, null,
                    null, null, isAvailable, pageable);
            //then
            assertNotNull(products);
            assertEquals(14, products.getTotalElements(), "Total elements should be 14");
            assertTrue(products.getContent().stream()
                            .allMatch(p -> productRepository.getReferenceById(p.id()).getProperties().stream()
                                    .anyMatch(pr -> pr.getQuantity() > 0)
                            ),
                    "At least one product quantity should be greater than 0");

        }

        @Test
        @Transactional
        @DisplayName("Get when isAvailable false must return 1 product")
        void getByFilters_when_isAvailable_false_present() {
            //given
            Boolean isAvailable = false;
            Pageable pageable = Pageable.unpaged();
            //when
            Page<ProductShortDto> products = productService.getByFilters(null, null, null,
                    null, null, isAvailable, pageable);
            //then
            assertNotNull(products);
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
        @DisplayName("Get when id 1L must return product with id 1L")
        void getById_when_id_1L() {
            //given
            Long id = 1L;
            //when
            Product product = productService.getById(id);
            //then
            assertNotNull(product);
            assertEquals(id, product.getId(), "Product id should be 1L");
        }

        @Test
        @Transactional
        @DisplayName("Get when id 100L must return AppException with message 'Not found product with id: 100' and status 404")
        void getById_when_id_100L() {
            //given
            Long id = 100L;
            //when
            Product product = null;
            try {
                product = productService.getById(id);
                fail("Exception AppException with status 404 did not throw!");
            } catch (AppException e) {
                //then
                assertEquals("Not found product with id: 100", e.getMessage());
                assertEquals(404, e.getStatus().value());
            }
        }
    }

    @Test
    @Transactional
    void getRecommendedProducts() {
        //given
        Long productId = 1L;
        Product product = productService.getById(productId);
        //when
        List<ProductShortDto> recommendedProducts = productService.getRecommendedProducts(productId);
        //then
        assertNotNull(recommendedProducts);
        assertTrue(recommendedProducts.size() < 6, "Recommended products size should be less than 6");
        assertTrue(recommendedProducts.size() == 4, "Recommended products size should be equal 4");
        assertTrue(recommendedProducts.stream()
                        .allMatch(p -> productRepository.getReferenceById(p.id())
                                .getCategory().getId().equals(product.getCategory().getId())
                        ),
                "All recommended products must content category id: 1L");

    }
}
