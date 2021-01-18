package com.springboot.SattimSatiyorum.service.feature;

import com.springboot.SattimSatiyorum.dao.feature.FeatureOptionRepository;
import com.springboot.SattimSatiyorum.entity.feature.FeatureOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FeatureOptionServiceImpl implements FeatureOptionService {

    private final FeatureOptionRepository featureOptionRepository;
    private final int perPage = 5;

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
    public ArrayList<FeatureOption> findFeatureOptionsOfProduct(int page, int productId) {
        page = Math.max(page, 1);
        return (ArrayList<FeatureOption>) featureOptionRepository.findFeatureOptionsOfProduct(perPage * page - perPage, perPage * page, productId);
    }


    @Override
    public ArrayList<FeatureOption> findFeatureOptionsOfFeature(int page, int featureId) {
        page = Math.max(page, 1);
        return (ArrayList<FeatureOption>) featureOptionRepository.findFeatureOptionsOfFeature(perPage * page - perPage, perPage * page, featureId);
    }

    @Override
    public void deleteById(int featureOptionId) {
        featureOptionRepository.deleteById(featureOptionId);
    }

}
