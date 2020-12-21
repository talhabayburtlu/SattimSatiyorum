package com.springboot.SattimSatiyorum.entity.feature;

import com.springboot.SattimSatiyorum.entity.Category;

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
            name = "Category_Features",
            joinColumns = {@JoinColumn(name = "feature_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    private List<Category> categories;

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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
