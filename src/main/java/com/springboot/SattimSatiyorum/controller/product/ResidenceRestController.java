package com.springboot.SattimSatiyorum.controller.product;

import com.springboot.SattimSatiyorum.dto.product.ResidenceDTO;
import com.springboot.SattimSatiyorum.entity.feature.FeatureOption;
import com.springboot.SattimSatiyorum.entity.product.Residence;
import com.springboot.SattimSatiyorum.service.feature.FeatureOptionService;
import com.springboot.SattimSatiyorum.service.product.ResidenceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ResidenceRestController {

    private final ResidenceService residenceService;
    private final FeatureOptionService featureOptionService;
    private final ModelMapper modelMapper;

    @Autowired
    public ResidenceRestController(ResidenceService residenceService, FeatureOptionService featureOptionService) {
        this.residenceService = residenceService;
        this.featureOptionService = featureOptionService;
        this.modelMapper = new ModelMapper();
    }

    @GetMapping("/residences/{residenceId}")
    public ResidenceDTO findById(@PathVariable int residenceId) {
        Residence residence = (Residence) residenceService.findById(residenceId);
        return toDTO(residence);
    }

    @PostMapping("/residences")
    public ResidenceDTO addResidence(@RequestBody ResidenceDTO residenceDTO) {
        Residence residence = toEntity(residenceDTO);
        residence.setId(0);
        residenceService.save(residence);
        return toDTO(residence);
    }

    @DeleteMapping("/residences/{residenceId}")
    public ResidenceDTO deleteResidence(@PathVariable int residenceId) {
        Residence residence = (Residence) residenceService.findById(residenceId);
        residenceService.deleteById(residenceId);
        return toDTO(residence);
    }

    private ResidenceDTO toDTO(Residence residence) {
        ArrayList<Integer> featureOptions = residence.getFeatureOptions()
                .stream()
                .map(FeatureOption::getId)
                .collect(Collectors.toCollection(ArrayList::new));
        ResidenceDTO residenceDTO = modelMapper.map(residence, ResidenceDTO.class);
        residenceDTO.setFeatureOptionIds(featureOptions);
        return residenceDTO;
    }

    private Residence toEntity(ResidenceDTO residenceDTO) {
        ArrayList<FeatureOption> featureOptions = residenceDTO.getFeatureOptionIds()
                .stream()
                .map(featureOptionService::findById)
                .collect(Collectors.toCollection(ArrayList::new));
        Residence residence = modelMapper.map(residenceDTO, Residence.class);
        residence.setFeatureOptions(featureOptions);
        return residence;
    }
}
