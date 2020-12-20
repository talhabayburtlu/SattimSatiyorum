package com.springboot.SattimSatiyorum.dto.feature;

public class FeatureDTO {
    private int id;
    private String description;
    private Boolean isMultipleChoice;
    private int[] featureTypeIds;

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

    public int[] getFeatureTypeIds() {
        return featureTypeIds;
    }

    public void setFeatureTypeIds(int[] featureTypeIds) {
        this.featureTypeIds = featureTypeIds;
    }
}
