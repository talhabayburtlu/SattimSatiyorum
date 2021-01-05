package com.springboot.SattimSatiyorum.service.feature;

import com.springboot.SattimSatiyorum.dao.feature.FeatureOptionRepository;
import com.springboot.SattimSatiyorum.entity.feature.FeatureOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeatureOptionServiceImpl implements FeatureOptionService {

    private final FeatureOptionRepository featureOptionRepository;

    @Autowired
    public FeatureOptionServiceImpl(FeatureOptionRepository featureOptionRepository) {
        this.featureOptionRepository = featureOptionRepository;
    }

    @Override
    public FeatureOption findById(int featureOptionId) {
        FeatureOption featureOption = featureOptionRepository.findById(featureOptionId).orElse(null);

        if (featureOption == null)
            throw new RuntimeException("FeatureOption with id: " + featureOptionId + " can't found.");

        return featureOption;
    }

    @Override
    public void save(FeatureOption featureOption) {
        featureOption.getFeature().getFeatureOptions().add(featureOption);
        featureOptionRepository.save(featureOption);
    }

    @Override
    public void deleteById(int featureOptionId) {
        featureOptionRepository.deleteById(featureOptionId);
    }

}
