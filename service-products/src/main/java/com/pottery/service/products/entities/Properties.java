package com.pottery.service.products.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "properties")
public class Properties {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "dimensions", length = 100)
    private String dimensions;

    @ManyToOne
    @JoinColumn(name = "material_id")
    private Material material;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @Column(name = "default_price", precision = 6, scale = 2)
    private BigDecimal defaultPrice;

    @Column(name = "discount_price", precision = 6, scale = 2)
    private BigDecimal discountPrice;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "sold")
    private Integer sold;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
