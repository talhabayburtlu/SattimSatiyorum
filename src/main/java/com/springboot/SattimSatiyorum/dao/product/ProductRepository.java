package com.springboot.SattimSatiyorum.dao.product;

import com.springboot.SattimSatiyorum.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

// @NoRepositoryBean
public interface ProductRepository<T extends Product> extends JpaRepository<T, Integer> {

}
