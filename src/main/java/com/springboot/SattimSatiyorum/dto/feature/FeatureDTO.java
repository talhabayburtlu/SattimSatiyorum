package com.springboot.SattimSatiyorum.dto.feature;

import java.util.ArrayList;

public class FeatureDTO {
    private int id;
    private String description;
    private Boolean isMultipleChoice;
    private ArrayList<Integer> featureOptionsIds;

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

    public ArrayList<Integer> getFeatureOptionsIds() {
        return featureOptionsIds;
    }

    public void setFeatureOptionsIds(ArrayList<Integer> featureOptionsIds) {
        this.featureOptionsIds = featureOptionsIds;
    }
}
