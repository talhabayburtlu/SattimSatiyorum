package com.springboot.SattimSatiyorum.rest;

import com.springboot.SattimSatiyorum.entity.Package;
import com.springboot.SattimSatiyorum.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PackageRestController {

    private final PackageService packageService;

    @Autowired
    public PackageRestController(PackageService packageService) {
        this.packageService = packageService;
    }

    @GetMapping("/packages/{packageId}")
    public Package getPackage(@PathVariable int packageId) {
        Package aPackage = packageService.findById(packageId); // throws error if does not exist

        return aPackage;
    }
    
}
