package com.springboot.SattimSatiyorum.entity.product;

import com.springboot.SattimSatiyorum.entity.Commercial;
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

    @OneToOne(mappedBy = "product")
    private Commercial commercial;

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

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public Commercial getCommercial() {
        return commercial;
    }

    public void setCommercial(Commercial commercial) {
        this.commercial = commercial;
    }


}
