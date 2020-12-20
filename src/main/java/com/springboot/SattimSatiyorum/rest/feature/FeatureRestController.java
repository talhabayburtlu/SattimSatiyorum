package com.springboot.SattimSatiyorum.rest.feature;

import com.springboot.SattimSatiyorum.dto.feature.FeatureDTO;
import com.springboot.SattimSatiyorum.entity.feature.Feature;
import com.springboot.SattimSatiyorum.entity.feature.FeatureType;
import com.springboot.SattimSatiyorum.entity.product.Product;
import com.springboot.SattimSatiyorum.service.feature.FeatureService;
import com.springboot.SattimSatiyorum.service.feature.FeatureTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class FeatureRestController {

    private final FeatureService featureService;
    private final FeatureTypeService featureTypeService;
    private final ModelMapper modelMapper;

    @Autowired
    public FeatureRestController(FeatureService featureService, FeatureTypeService featureTypeService) {
        this.featureService = featureService;
        this.featureTypeService = featureTypeService;
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

    private FeatureDTO toDTO(Feature feature) {
        ArrayList<Integer> featureTypeIds = feature.getFeatureTypes()
                .stream().map(f -> f.getId()).collect(Collectors.toCollection(ArrayList::new));

        FeatureDTO featureDTO = modelMapper.map(feature, FeatureDTO.class);
        featureDTO.setFeatureTypeIds(featureTypeIds);
        return featureDTO;
    }

    private Feature toEntity(FeatureDTO featureDTO) {
        ArrayList<FeatureType> featureTypes = featureDTO.getFeatureTypeIds() != null ?
                featureDTO.getFeatureTypeIds()
                        .stream().map(id -> featureTypeService.findById(id)).collect(Collectors.toCollection(ArrayList::new))
                : new ArrayList<>();
        ArrayList<Product> products = new ArrayList<>();

        Feature feature = modelMapper.map(featureDTO, Feature.class);
        feature.setFeatureTypes(featureTypes);
        feature.setProducts(products);
        return feature;
    }

}
