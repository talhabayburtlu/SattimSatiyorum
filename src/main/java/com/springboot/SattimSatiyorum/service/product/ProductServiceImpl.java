package com.springboot.SattimSatiyorum.service.product;

import com.springboot.SattimSatiyorum.dao.product.ProductRepository;
import com.springboot.SattimSatiyorum.entity.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class ProductServiceImpl implements ProductService {

    private final ProductRepository<Product> productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository<Product> productRepository) {
        this.productRepository = productRepository;
    }

    public Product findById(int productId) {
        Product product = productRepository.findById(productId).get();

        if (product == null)
            throw new RuntimeException("Product with id: " + productId + " can't found.");

        return product;
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void deleteById(int productId) {
        productRepository.deleteById(productId);
    }

}
