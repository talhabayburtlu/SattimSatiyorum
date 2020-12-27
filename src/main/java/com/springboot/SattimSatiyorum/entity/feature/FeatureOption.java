package com.springboot.SattimSatiyorum.entity.feature;

import com.springboot.SattimSatiyorum.entity.product.Product;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Feature_Option")
public class FeatureOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "feature_id", nullable = false)
    private Feature feature;

    @ManyToMany(mappedBy = "featureOptions")
    private List<Product> products;

    public FeatureOption() {
    }

    public FeatureOption(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }
}
