package com.springboot.SattimSatiyorum.service.feature;

import com.springboot.SattimSatiyorum.dao.feature.FeatureRepository;
import com.springboot.SattimSatiyorum.entity.feature.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeatureServiceImpl implements FeatureService {

    private final FeatureRepository featureRepository;

    @Autowired
    public FeatureServiceImpl(FeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }

    @Override
    public Feature findById(int featureId) {
        Feature feature = featureRepository.findById(featureId).orElse(null);

        if (feature == null)
            throw new RuntimeException("Feature with id: " + featureId + " can't found.");

        return feature;
    }

    @Override
    public void save(Feature feature) {
        featureRepository.save(feature);
    }

    @Override
    public void deleteById(int featureId) {
        featureRepository.deleteById(featureId);
    }
}
