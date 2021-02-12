package com.springboot.SattimSatiyorum.controller.product;

import com.springboot.SattimSatiyorum.dto.product.VehicleDTO;
import com.springboot.SattimSatiyorum.entity.feature.FeatureOption;
import com.springboot.SattimSatiyorum.entity.product.Product;
import com.springboot.SattimSatiyorum.entity.product.Vehicle;
import com.springboot.SattimSatiyorum.service.feature.FeatureOptionService;
import com.springboot.SattimSatiyorum.service.product.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public VehicleDTO deleteVehicle(@PathVariable int vehicleId) throws Exception {
        Vehicle vehicle = (Vehicle) vehicleService.findById(vehicleId);

        org.springframework.security.core.userdetails.User securityUser =
                (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!securityUser.getUsername().equals(vehicle.getCommercial().getSeller().getMail()))
            throw new Exception("You are not authorized to delete this commercial.");

        vehicleService.deleteById(vehicleId);
        return toDTO(vehicle);
    }

    @GetMapping("/vehicles")
    public ArrayList<VehicleDTO> getResidenceByHeader(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "") String subHeader) {
        System.out.println(subHeader);
        ArrayList<Product> products = vehicleService.findProductByHeader(page, "Vehicle", subHeader);
        return toDTOList(products.stream().map(p -> (Vehicle) p).collect(Collectors.toCollection(ArrayList::new)));
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

    private ArrayList<VehicleDTO> toDTOList(ArrayList<Vehicle> vehicles) {
        return vehicles.stream().map(this::toDTO).collect(Collectors.toCollection(ArrayList::new));
    }

}
