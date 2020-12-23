package com.springboot.SattimSatiyorum.service;

import com.springboot.SattimSatiyorum.dao.CategoryRepository;
import com.springboot.SattimSatiyorum.entity.Category;
import com.springboot.SattimSatiyorum.entity.feature.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findById(int categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);

        if (category == null)
            throw new RuntimeException("Category with id: " + categoryId + " can't found.");

        return category;
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void deleteById(int categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public void createCategoryFeature(Category category, Feature feature) {
        category.getFeatures().add(feature);
        feature.getCategories().add(category);
    }


}
