package com.springboot.SattimSatiyorum.service;

import com.springboot.SattimSatiyorum.entity.Category;
import com.springboot.SattimSatiyorum.entity.feature.Feature;

public interface CategoryService {

    Category findById(int categoryId);

    void save(Category category);

    void deleteById(int categoryId);

    void createCategoryFeature(Category category, Feature feature);

}
