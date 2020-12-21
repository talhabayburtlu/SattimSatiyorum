package com.springboot.SattimSatiyorum.rest;

import com.springboot.SattimSatiyorum.dto.UserDTO;
import com.springboot.SattimSatiyorum.entity.Commercial;
import com.springboot.SattimSatiyorum.entity.Package;
import com.springboot.SattimSatiyorum.entity.User;
import com.springboot.SattimSatiyorum.service.CommercialService;
import com.springboot.SattimSatiyorum.service.PackageService;
import com.springboot.SattimSatiyorum.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private final UserService userService;
    private final CommercialService commercialService;
    private final PackageService packageService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserRestController(UserService userService, CommercialService commercialService, PackageService packageService) {
        this.userService = userService;
        this.commercialService = commercialService;
        this.packageService = packageService;
        this.modelMapper = new ModelMapper();
    }

    @GetMapping("/users/{userId}")
    public UserDTO getUser(@PathVariable int userId) {
        User user = userService.findById(userId); // throws error if does not exist
        return toDTO(user);
    }

    @PostMapping("/users")
    public UserDTO addUser(@RequestBody UserDTO userDTO) {
        Package aPackage = packageService.findById(1);
        User user = toEntity(userDTO);
        user.setId(0);
        userService.save(user);
        return toDTO(user);
    }

    @DeleteMapping("/users/{userId}")
    public UserDTO deleteUser(@PathVariable int userId) {
        User user = userService.findById(userId); // throws error if does not exist
        userService.deleteById(userId);
        return toDTO(user);
    }

    private UserDTO toDTO(User user) {
        ArrayList<Integer> createdCommercialIds = user.getCreatedCommercials() != null ? user.getCreatedCommercials().stream()
                .map(Commercial::getId)
                .collect(Collectors.toCollection(ArrayList::new))
                : new ArrayList<>();
        ArrayList<Integer> appliedCommercialIds = user.getAppliedCommercials() != null ? user.getCreatedCommercials().stream()
                .map(Commercial::getId)
                .collect(Collectors.toCollection(ArrayList::new))
                : new ArrayList<>();
        int packageId = user.getaPackage().getId();

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        userDTO.setCreatedCommercialIds(createdCommercialIds);
        userDTO.setAppliedCommercialIds(appliedCommercialIds);
        userDTO.setPackage_id(packageId);
        return userDTO;
    }

    private User toEntity(UserDTO userDTO) {
        ArrayList<Commercial> createdCommercials = new ArrayList<>();
        ArrayList<Commercial> appliedCommercials = new ArrayList<>();
        Package aPackage = packageService.findById(1);

        User user = modelMapper.map(userDTO, User.class);
        user.setCreatedCommercials(createdCommercials);
        user.setAppliedCommercials(appliedCommercials);
        user.setaPackage(aPackage);

        return user;
    }

}
