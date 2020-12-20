package com.springboot.SattimSatiyorum.service.feature;

import com.springboot.SattimSatiyorum.dao.feature.FeatureTypeRepository;
import com.springboot.SattimSatiyorum.entity.feature.FeatureType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeatureTypeServiceImpl implements FeatureTypeService {

    private final FeatureTypeRepository featureTypeRepository;

    @Autowired
    public FeatureTypeServiceImpl(FeatureTypeRepository featureTypeRepository) {
        this.featureTypeRepository = featureTypeRepository;
    }

    @Override
    public FeatureType findById(int featureTypeId) {
        FeatureType featureType = featureTypeRepository.findById(featureTypeId).orElse(null);

        if (featureType == null)
            throw new RuntimeException("FeatureType with id: " + featureTypeId + " can't found.");

        return featureType;
    }

    @Override
    public void save(FeatureType featureType) {
        featureType.getFeature().getFeatureTypes().add(featureType);
        featureTypeRepository.save(featureType);
    }

    @Override
    public void deleteById(int featureTypeId) {
        featureTypeRepository.deleteById(featureTypeId);
    }

}
