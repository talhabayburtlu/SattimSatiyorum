package com.springboot.SattimSatiyorum.service.product;

import com.springboot.SattimSatiyorum.entity.product.Product;

public interface ProductService {

    Product findById(int productId);

    void save(Product product);

    void deleteById(int productId);

}
