package com.springboot.SattimSatiyorum.entity.feature;

import com.springboot.SattimSatiyorum.entity.product.Product;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Feature")
public class Feature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "description")
    private String description;

    @Column(name = "is_multiple_choice")
    private Boolean isMultipleChoice;

    @OneToMany(mappedBy = "feature")
    private List<FeatureType> featureTypes;

    @ManyToMany()
    @JoinTable(
            name = "Product_Feature",
            joinColumns = {@JoinColumn(name = "feature_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    private List<Product> products;

    public Feature() {
    }

    public Feature(String description, Boolean isMultipleChoice) {
        this.description = description;
        this.isMultipleChoice = isMultipleChoice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getMultipleChoice() {
        return isMultipleChoice;
    }

    public void setMultipleChoice(Boolean multipleChoice) {
        isMultipleChoice = multipleChoice;
    }

    public List<FeatureType> getFeatureTypes() {
        return featureTypes;
    }

    public void setFeatureTypes(List<FeatureType> featureTypes) {
        this.featureTypes = featureTypes;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
