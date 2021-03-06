package com.springboot.SattimSatiyorum.service.product;

import com.springboot.SattimSatiyorum.dao.product.ProductRepository;
import com.springboot.SattimSatiyorum.entity.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository<Product> productRepository;
    private final int perPage = 5;

    @Autowired
    public ProductServiceImpl(ProductRepository<Product> productRepository) {
        this.productRepository = productRepository;
    }

    public Product findById(int productId) {
        Product product = productRepository.findById(productId).orElse(null);

        if (product == null)
            throw new RuntimeException("Product with id: " + productId + " can't found.");

        return product;
    }

    public ArrayList<Product> findProductByHeader(int page, String type, String subHeader) {
        page = Math.max(page, 1);
        return (ArrayList<Product>) productRepository.findProductByHeader(perPage * page - perPage, perPage * page, type, subHeader.toLowerCase());
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void deleteById(int productId) {
        productRepository.deleteById(productId);
    }

}