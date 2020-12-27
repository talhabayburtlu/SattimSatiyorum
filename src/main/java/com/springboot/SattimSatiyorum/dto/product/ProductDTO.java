package com.springboot.SattimSatiyorum.dto.product;

import java.util.ArrayList;

public class ProductDTO {
    private int id;
    private String header;
    private String description;
    private String type;
    private int commercial_id;
    private ArrayList<Integer> featureOptionIds;

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

    public int getCommercial_id() {
        return commercial_id;
    }

    public void setCommercial_id(int commercial_id) {
        this.commercial_id = commercial_id;
    }

    public ArrayList<Integer> getFeatureOptionIds() {
        return featureOptionIds;
    }

    public void setFeatureOptionIds(ArrayList<Integer> featureOptionIds) {
        this.featureOptionIds = featureOptionIds;
    }
}
