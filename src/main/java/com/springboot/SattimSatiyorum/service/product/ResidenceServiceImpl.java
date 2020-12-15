package com.springboot.SattimSatiyorum.service.product;

import com.springboot.SattimSatiyorum.dao.product.ProductRepository;
import com.springboot.SattimSatiyorum.dao.product.ResidenceRepository;
import com.springboot.SattimSatiyorum.entity.product.Product;
import org.springframework.beans.factory.annotation.Autowired;

public class ResidenceServiceImpl extends ProductServiceImpl implements ResidenceService {

    ResidenceRepository residenceRepository;

    @Autowired
    public ResidenceServiceImpl(ProductRepository<Product> productRepository, ResidenceRepository residenceRepository) {
        super(productRepository);
        this.residenceRepository = residenceRepository;
    }

    @Override
    public void save(Product product) {

    }

    @Override
    public void deleteById(int productId) {

    }
}
