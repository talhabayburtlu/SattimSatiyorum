package com.springboot.SattimSatiyorum.service.product;

import com.springboot.SattimSatiyorum.dao.product.ProductRepository;
import com.springboot.SattimSatiyorum.dao.product.VehicleRepository;
import com.springboot.SattimSatiyorum.entity.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl extends ProductServiceImpl implements VehicleService {

    VehicleRepository vehicleRepository;

    @Autowired
    public VehicleServiceImpl(ProductRepository<Product> productRepository, VehicleRepository vehicleRepository) {
        super(productRepository);
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public void save(Product product) {

    }

    @Override
    public void deleteById(int productId) {

    }
}
