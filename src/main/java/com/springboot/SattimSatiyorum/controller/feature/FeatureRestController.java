package com.springboot.SattimSatiyorum.controller.feature;

import com.springboot.SattimSatiyorum.dto.feature.FeatureDTO;
import com.springboot.SattimSatiyorum.entity.feature.Feature;
import com.springboot.SattimSatiyorum.entity.feature.FeatureOption;
import com.springboot.SattimSatiyorum.service.feature.FeatureOptionService;
import com.springboot.SattimSatiyorum.service.feature.FeatureService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class FeatureRestController {

    private final FeatureService featureService;
    private final FeatureOptionService featureOptionService;
    private final ModelMapper modelMapper;

    @Autowired
    public FeatureRestController(FeatureService featureService, FeatureOptionService featureOptionService) {
        this.featureService = featureService;
        this.featureOptionService = featureOptionService;
        this.modelMapper = new ModelMapper();
    }

    @GetMapping("/features/{featureId}")
    public FeatureDTO getFeature(@PathVariable int featureId) {
        Feature feature = featureService.findById(featureId);

        return toDTO(feature);
    }

    @PostMapping("/features")
    public FeatureDTO addFeature(@RequestBody FeatureDTO featureDTO) {
        Feature feature = toEntity(featureDTO);
        feature.setId(0);
        featureService.save(feature);

        return toDTO(feature);
    }

    @DeleteMapping("/features/{featureId}")
    public FeatureDTO deleteFeature(@PathVariable int featureId) {
        Feature feature = featureService.findById(featureId);
        featureService.deleteById(featureId);

        return toDTO(feature);
    }

    @GetMapping("/features/featureOptions/{featureOptionId}")
    public FeatureDTO getFeatureFromFeatureOption(@PathVariable int featureOptionId) {
        return toDTO(featureService.findFeatureFromFeatureOption(featureOptionId));
    }

    private FeatureDTO toDTO(Feature feature) {
        ArrayList<Integer> featureOptionIds = feature.getFeatureOptions()
                .stream().map(FeatureOption::getId).collect(Collectors.toCollection(ArrayList::new));

        FeatureDTO featureDTO = modelMapper.map(feature, FeatureDTO.class);
        featureDTO.setFeatureOptionsIds(featureOptionIds);
        return featureDTO;
    }

    private Feature toEntity(FeatureDTO featureDTO) {
        ArrayList<FeatureOption> featureOptions = new ArrayList<>();

        Feature feature = modelMapper.map(featureDTO, Feature.class);
        feature.setFeatureOptions(featureOptions);
        return feature;
    }

}
