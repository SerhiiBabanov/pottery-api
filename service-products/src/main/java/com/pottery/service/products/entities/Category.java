package com.pottery.service.products.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", length = 50)
    private String name;

    @ManyToMany
    @JoinTable(name = "categories_categories",
            joinColumns = @JoinColumn(name = "category_1_id"),
            inverseJoinColumns = @JoinColumn(name = "categories_2_id"))
    private Set<Category> categories = new LinkedHashSet<>();

    @OneToMany(mappedBy = "category", orphanRemoval = true)
    @OrderBy("id DESC")
    private List<Product> products = new ArrayList<>();

}
