package com.springboot.SattimSatiyorum.service.product;

import com.springboot.SattimSatiyorum.dao.product.ProductRepository;
import com.springboot.SattimSatiyorum.dao.product.ShoppingRepository;
import com.springboot.SattimSatiyorum.entity.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingServiceImpl extends ProductServiceImpl implements ShoppingService {

    ShoppingRepository shoppingRepository;

    @Autowired
    public ShoppingServiceImpl(ProductRepository<Product> productRepository, ShoppingRepository shoppingRepository) {
        super(productRepository);
        this.shoppingRepository = shoppingRepository;
    }

    @Override
    public void save(Product product) {

    }

    @Override
    public void deleteById(int productId) {

    }
}
