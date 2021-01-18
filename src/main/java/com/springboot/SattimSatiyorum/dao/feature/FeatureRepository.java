package com.springboot.SattimSatiyorum.dao.feature;

import com.springboot.SattimSatiyorum.entity.feature.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface FeatureRepository extends JpaRepository<Feature, Integer> {

    @Transactional
    @Query(value = "SELECT f.* " +
            "FROM Feature f inner join Feature_Option fo on f.id = fo.feature_id " +
            "WHERE fo.id = :featureOptionId AND fo.feature_id = f.id ", nativeQuery = true)
    Feature findFeatureFromFeatureOption(@Param("featureOptionId") int featureOptionId);

}
