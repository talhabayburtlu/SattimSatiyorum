package com.springboot.SattimSatiyorum.service;

import com.springboot.SattimSatiyorum.entity.Category;

public interface CategoryService {

    Category findById(int categoryId);

    void save(Category category);

    void deleteById(int categoryId);

}
