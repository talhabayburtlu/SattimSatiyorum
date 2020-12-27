package com.springboot.SattimSatiyorum.entity.product;

import com.springboot.SattimSatiyorum.entity.Commercial;
import com.springboot.SattimSatiyorum.entity.feature.FeatureOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Product")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type",
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

    @Column(name = "type", insertable = false, updatable = false)
    private String type;

    @OneToOne(mappedBy = "product")
    private Commercial commercial;

    @ManyToMany()
    @JoinTable(
            name = "Product_Feature_Options",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "feature_option_id")})
    private List<FeatureOption> featureOptions;

    public Product() {
    }

    public Product(String header, String description, String type) {
        this.header = header;
        this.description = description;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Commercial getCommercial() {
        return commercial;
    }

    public void setCommercial(Commercial commercial) {
        this.commercial = commercial;
    }

    public List<FeatureOption> getFeatureOptions() {
        return featureOptions;
    }

    public void setFeatureOptions(List<FeatureOption> featureOptions) {
        this.featureOptions = featureOptions;
    }
}


