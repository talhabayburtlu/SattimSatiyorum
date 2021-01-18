package com.springboot.SattimSatiyorum.service.feature;

import com.springboot.SattimSatiyorum.entity.feature.Feature;

public interface FeatureService {

    Feature findById(int featureId);

    void save(Feature feature);

    void deleteById(int featureId);

    Feature findFeatureFromFeatureOption(int featureOptionId);

}
