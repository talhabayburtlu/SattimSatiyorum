package com.springboot.SattimSatiyorum.rest.feature;


import com.springboot.SattimSatiyorum.dto.feature.FeatureTypeDTO;
import com.springboot.SattimSatiyorum.entity.feature.Feature;
import com.springboot.SattimSatiyorum.entity.feature.FeatureType;
import com.springboot.SattimSatiyorum.service.feature.FeatureService;
import com.springboot.SattimSatiyorum.service.feature.FeatureTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FeatureTypeRestController {

    private final FeatureTypeService featureTypeService;
    private final FeatureService featureService;
    private final ModelMapper modelMapper;

    @Autowired
    public FeatureTypeRestController(FeatureTypeService featureTypeService, FeatureService featureService) {
        this.featureTypeService = featureTypeService;
        this.featureService = featureService;
        this.modelMapper = new ModelMapper();
    }

    @GetMapping("/featureTypes/{featureTypeId}")
    public FeatureTypeDTO getFeatureType(@PathVariable int featureTypeId) {
        FeatureType feature = featureTypeService.findById(featureTypeId);
        return toDTO(feature);
    }

    @PostMapping("/featureTypes")
    public FeatureTypeDTO addFeatureType(@RequestBody FeatureTypeDTO featureTypeDTO) {
        FeatureType featureType = toEntity(featureTypeDTO);
        featureType.setId(0);
        featureTypeService.save(featureType);

        return toDTO(featureType);
    }

    @DeleteMapping("/featureTypes/{featureTypeId}")
    public FeatureTypeDTO deleteFeatureType(@PathVariable int featureTypeId) {
        FeatureType featureType = featureTypeService.findById(featureTypeId);
        featureTypeService.deleteById(featureTypeId);

        return toDTO(featureType);
    }

    private FeatureTypeDTO toDTO(FeatureType featureType) {
        int featureId = featureType.getFeature().getId();
        return modelMapper.map(featureType, FeatureTypeDTO.class);
    }

    private FeatureType toEntity(FeatureTypeDTO featureTypeDTO) {
        Feature feature = featureService.findById(featureTypeDTO.getFeatureId());
        FeatureType featureType = modelMapper.map(featureTypeDTO, FeatureType.class);
        featureType.setFeature(feature);
        return featureType;
    }


}
