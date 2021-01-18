package com.springboot.SattimSatiyorum.service.product;

import com.springboot.SattimSatiyorum.entity.product.Product;

import java.util.ArrayList;

public interface ProductService {

    Product findById(int productId);

    void save(Product product);

    void deleteById(int productId);

    ArrayList<Product> findProductByHeader(int page, String type, String subHeader);

}
