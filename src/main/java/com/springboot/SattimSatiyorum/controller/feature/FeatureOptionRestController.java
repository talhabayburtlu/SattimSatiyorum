package com.springboot.SattimSatiyorum.controller.feature;


import com.springboot.SattimSatiyorum.dto.feature.FeatureOptionDTO;
import com.springboot.SattimSatiyorum.entity.feature.Feature;
import com.springboot.SattimSatiyorum.entity.feature.FeatureOption;
import com.springboot.SattimSatiyorum.service.feature.FeatureOptionService;
import com.springboot.SattimSatiyorum.service.feature.FeatureService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FeatureOptionRestController {

    private final FeatureOptionService featureOptionService;
    private final FeatureService featureService;
    private final ModelMapper modelMapper;

    @Autowired
    public FeatureOptionRestController(FeatureOptionService featureOptionService, FeatureService featureService) {
        this.featureOptionService = featureOptionService;
        this.featureService = featureService;
        this.modelMapper = new ModelMapper();
    }

    @GetMapping("/featureOptions/{featureOptionId}")
    public FeatureOptionDTO getFeatureOption(@PathVariable int featureOptionId) {
        FeatureOption feature = featureOptionService.findById(featureOptionId);
        return toDTO(feature);
    }

    @PostMapping("/featureOptions")
    public FeatureOptionDTO addFeatureOption(@RequestBody FeatureOptionDTO featureOptionDTO) {
        FeatureOption featureOption = toEntity(featureOptionDTO);
        featureOption.setId(0);
        featureOptionService.save(featureOption);

        return toDTO(featureOption);
    }

    @DeleteMapping("/featureOptions/{featureOptionId}")
    public FeatureOptionDTO deleteFeatureOption(@PathVariable int featureOptionId) {
        FeatureOption featureOption = featureOptionService.findById(featureOptionId);
        featureOptionService.deleteById(featureOptionId);

        return toDTO(featureOption);
    }

    private FeatureOptionDTO toDTO(FeatureOption featureOption) {
        int featureId = featureOption.getFeature().getId();
        FeatureOptionDTO featureOptionDTO = modelMapper.map(featureOption, FeatureOptionDTO.class);
        featureOptionDTO.setFeatureId(featureId);
        return featureOptionDTO;
    }

    private FeatureOption toEntity(FeatureOptionDTO featureOptionDTO) {
        Feature feature = featureService.findById(featureOptionDTO.getFeatureId());
        FeatureOption featureOption = modelMapper.map(featureOptionDTO, FeatureOption.class);
        featureOption.setFeature(feature);
        return featureOption;
    }


}
