package com.springboot.SattimSatiyorum.entity.feature;

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
    private List<FeatureOption> featureOptions;

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

    public List<FeatureOption> getFeatureTypes() {
        return featureOptions;
    }

    public void setFeatureTypes(List<FeatureOption> featureOptions) {
        this.featureOptions = featureOptions;
    }

    public List<FeatureOption> getFeatureOptions() {
        return featureOptions;
    }

    public void setFeatureOptions(List<FeatureOption> featureOptions) {
        this.featureOptions = featureOptions;
    }


}
