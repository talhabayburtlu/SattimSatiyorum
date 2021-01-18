package com.springboot.SattimSatiyorum.service.feature;

import com.springboot.SattimSatiyorum.entity.feature.FeatureOption;

import java.util.ArrayList;

public interface FeatureOptionService {

    FeatureOption findById(int featureOptionId);

    void save(FeatureOption featureOption);

    void deleteById(int featureOptionId);

    ArrayList<FeatureOption> findFeatureOptionsOfProduct(int page, int productId);

    ArrayList<FeatureOption> findFeatureOptionsOfFeature(int page, int featureId);


}
