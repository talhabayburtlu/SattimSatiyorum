package com.springboot.SattimSatiyorum.controller;

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
        return packageService.findById(packageId); // throws error if does not exist;
    }

    @GetMapping("/packages/user/{userId}")
    public Package getPackageOfUser(@PathVariable int userId) {
        return packageService.findPackageOfUser(userId);
    }

}
