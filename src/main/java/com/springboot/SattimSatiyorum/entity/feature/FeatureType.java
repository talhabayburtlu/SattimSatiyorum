package com.springboot.SattimSatiyorum.entity.feature;

import javax.persistence.*;

@Entity
@Table(name = "Feature_Type")
public class FeatureType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "feature_id", nullable = false)
    private Feature feature;

    public FeatureType() {
    }

    public FeatureType(String type) {
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
}
