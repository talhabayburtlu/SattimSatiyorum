package com.springboot.SattimSatiyorum.rest.product;

import com.springboot.SattimSatiyorum.entity.product.Vehicle;
import com.springboot.SattimSatiyorum.service.product.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

public class VehicleRestController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleRestController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/vehicles/{vehicleId}")
    public Vehicle findById(@PathVariable int vehicleId) {
        Vehicle vehicle = (Vehicle) vehicleService.findById(vehicleId);

        return vehicle;
    }

    @PostMapping("/vehicles")
    public Vehicle addVehicle(@RequestBody Vehicle vehicle) {
        vehicle.setId(0);
        vehicleService.save(vehicle);

        return vehicle;
    }

    @DeleteMapping("/vehicles/{vehicleId}")
    public Vehicle deleteVehicle(@PathVariable int vehicleId) {
        Vehicle vehicle = (Vehicle) vehicleService.findById(vehicleId);

        vehicleService.deleteById(vehicleId);

        return vehicle;
    }

}
