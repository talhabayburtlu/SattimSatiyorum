package com.springboot.SattimSatiyorum.dto;

import java.util.ArrayList;

public class CategoryDTO {
    private int id;
    private String name;
    private ArrayList<Integer> featureIds;
    private ArrayList<Integer> productIds;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Integer> getFeatureIds() {
        return featureIds;
    }

    public void setFeatureIds(ArrayList<Integer> featureIds) {
        this.featureIds = featureIds;
    }

    public ArrayList<Integer> getProductIds() {
        return productIds;
    }

    public void setProductIds(ArrayList<Integer> productIds) {
        this.productIds = productIds;
    }
}
