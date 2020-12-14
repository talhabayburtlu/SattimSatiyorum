package com.springboot.SattimSatiyorum.entity.product;

import com.springboot.SattimSatiyorum.entity.feature.Feature;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Product")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "category",
        discriminatorType = DiscriminatorType.STRING)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "header")
    private String header;

    @Column(name = "description")
    private String description;

    @Column(name = "category", insertable = false, updatable = false)
    private String category;

    @ManyToMany(mappedBy = "products")
    private List<Feature> features;

    public Product() {
    }

    public Product(String header, String description, String category) {
        this.header = header;
        this.description = description;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
