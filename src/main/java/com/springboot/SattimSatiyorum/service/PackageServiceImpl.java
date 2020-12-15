package com.springboot.SattimSatiyorum.service;

import com.springboot.SattimSatiyorum.dao.PackageRepository;
import com.springboot.SattimSatiyorum.entity.Package;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PackageServiceImpl implements PackageService {

    PackageRepository packageRepository;

    @Autowired
    public PackageServiceImpl(PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    @Override
    public Package findById(int packageId) {
        Package aPackage = packageRepository.findById(packageId).get();

        if (aPackage == null)
            throw new RuntimeException("Package with id: " + packageId + " can't found.");

        return aPackage;
    }
}
