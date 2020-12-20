package com.springboot.SattimSatiyorum.service.feature;

import com.springboot.SattimSatiyorum.entity.feature.FeatureType;

public interface FeatureTypeService {

    FeatureType findById(int featureTypeId);

    void save(FeatureType featureType);

    void deleteById(int featureTypeId);

}
