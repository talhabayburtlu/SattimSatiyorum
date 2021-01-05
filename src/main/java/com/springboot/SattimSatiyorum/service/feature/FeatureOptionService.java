package com.springboot.SattimSatiyorum.service.feature;

import com.springboot.SattimSatiyorum.entity.feature.FeatureOption;

public interface FeatureOptionService {

    FeatureOption findById(int featureOptionId);

    void save(FeatureOption featureOption);

    void deleteById(int featureOptionId);

}
