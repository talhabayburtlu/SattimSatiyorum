package com.springboot.SattimSatiyorum.dao.product;

import com.springboot.SattimSatiyorum.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}