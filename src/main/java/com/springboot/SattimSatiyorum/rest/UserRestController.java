package com.springboot.SattimSatiyorum.rest;

import com.springboot.SattimSatiyorum.entity.Package;
import com.springboot.SattimSatiyorum.entity.User;
import com.springboot.SattimSatiyorum.service.PackageService;
import com.springboot.SattimSatiyorum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private final UserService userService;
    private final PackageService packageService;

    @Autowired
    public UserRestController(UserService userService, PackageService packageService) {
        this.userService = userService;
        this.packageService = packageService;
    }

    @GetMapping("/users/{userId}")
    public User getUser(@PathVariable int userId) {
        User user = userService.findById(userId); // throws error if does not exist

        return user;
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        Package aPackage = packageService.findById(1);

        user.setId(0);
        user.setaPackage(aPackage);
        userService.save(user);

        return user;
    }

    @DeleteMapping("/users/{userId}")
    public User deleteUser(@PathVariable int userId) {
        User user = userService.findById(userId); // throws error if does not exist

        userService.deleteById(userId);

        return user;
    }

}
