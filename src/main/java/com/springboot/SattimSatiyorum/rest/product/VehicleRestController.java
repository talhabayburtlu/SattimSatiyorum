package com.springboot.SattimSatiyorum.rest.product;

import com.springboot.SattimSatiyorum.dto.product.VehicleDTO;
import com.springboot.SattimSatiyorum.entity.Category;
import com.springboot.SattimSatiyorum.entity.product.Vehicle;
import com.springboot.SattimSatiyorum.service.CategoryService;
import com.springboot.SattimSatiyorum.service.product.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

public class VehicleRestController {

    private final VehicleService vehicleService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public VehicleRestController(VehicleService vehicleService, CategoryService categoryService) {
        this.vehicleService = vehicleService;
        this.categoryService = categoryService;
        this.modelMapper = new ModelMapper();
    }

    @GetMapping("/vehicles/{vehicleId}")
    public VehicleDTO findById(@PathVariable int vehicleId) {
        Vehicle vehicle = (Vehicle) vehicleService.findById(vehicleId);
        return toDTO(vehicle);
    }

    @PostMapping("/vehicles")
    public VehicleDTO addVehicle(@RequestBody VehicleDTO vehicleDTO) {
        Vehicle vehicle = toEntity(vehicleDTO);
        vehicle.setId(0);
        vehicleService.save(vehicle);
        return toDTO(vehicle);
    }

    @DeleteMapping("/vehicles/{vehicleId}")
    public VehicleDTO deleteVehicle(@PathVariable int vehicleId) {
        Vehicle vehicle = (Vehicle) vehicleService.findById(vehicleId);
        vehicleService.deleteById(vehicleId);
        return toDTO(vehicle);
    }

    private VehicleDTO toDTO(Vehicle vehicle) {
        int category_id = vehicle.getCategory().getId();

        VehicleDTO vehicleDTO = modelMapper.map(vehicle, VehicleDTO.class);
        vehicleDTO.setCategory_id(category_id);
        return vehicleDTO;
    }

    private Vehicle toEntity(VehicleDTO vehicleDTO) {
        Category category = categoryService.findById(vehicleDTO.getCategory_id());

        Vehicle vehicle = modelMapper.map(vehicleDTO, Vehicle.class);
        vehicle.setCategory(category);
        return vehicle;
    }
}
