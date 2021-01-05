package com.springboot.SattimSatiyorum.controller.product;

import com.springboot.SattimSatiyorum.dto.product.VehicleDTO;
import com.springboot.SattimSatiyorum.entity.feature.FeatureOption;
import com.springboot.SattimSatiyorum.entity.product.Vehicle;
import com.springboot.SattimSatiyorum.service.feature.FeatureOptionService;
import com.springboot.SattimSatiyorum.service.product.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class VehicleRestController {

    private final VehicleService vehicleService;
    private final FeatureOptionService featureOptionService;
    private final ModelMapper modelMapper;

    @Autowired
    public VehicleRestController(VehicleService vehicleService, FeatureOptionService featureOptionService) {
        this.vehicleService = vehicleService;
        this.featureOptionService = featureOptionService;
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
        ArrayList<Integer> featureOptions = vehicle.getFeatureOptions()
                .stream()
                .map(FeatureOption::getId)
                .collect(Collectors.toCollection(ArrayList::new));
        VehicleDTO vehicleDTO = modelMapper.map(vehicle, VehicleDTO.class);
        vehicleDTO.setFeatureOptionIds(featureOptions);
        return vehicleDTO;
    }

    private Vehicle toEntity(VehicleDTO vehicleDTO) {
        ArrayList<FeatureOption> featureOptions = vehicleDTO.getFeatureOptionIds()
                .stream()
                .map(featureOptionService::findById)
                .collect(Collectors.toCollection(ArrayList::new));
        Vehicle vehicle = modelMapper.map(vehicleDTO, Vehicle.class);
        vehicle.setFeatureOptions(featureOptions);
        return vehicle;
    }
}