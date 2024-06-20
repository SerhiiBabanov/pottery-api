package com.pottery.service.products.entities;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "care_guide", length = 1000)
    private String careGuide;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Properties> properties;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private Map<Integer, String> images;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "product_id"))
    private Set<Collection> collections;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "catalog_price", precision = 6, scale = 2)
    private BigDecimal catalogPrice;

    @Column(name = "discount_catalog_price", precision = 6, scale = 2)
    private BigDecimal discountCatalogPrice;

}
