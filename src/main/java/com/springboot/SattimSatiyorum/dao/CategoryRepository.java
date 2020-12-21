package com.springboot.SattimSatiyorum.dao;

import com.springboot.SattimSatiyorum.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
