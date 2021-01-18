package com.springboot.SattimSatiyorum.dao.feature;

import com.springboot.SattimSatiyorum.entity.feature.FeatureOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface FeatureOptionRepository extends JpaRepository<FeatureOption, Integer> {

    @Transactional
    @Query(value = "SELECT fo.* " +
            "FROM Feature_Option fo inner join Product_Feature_Options pfo on fo.id = pfo.feature_option_id " +
            "   inner join Product p on pfo.product_id = p.id " +
            "WHERE p.id = :productId " +
            "ORDER BY fo.id " +
            "LIMIT :start,:end ",
            nativeQuery = true)
    List<FeatureOption> findFeatureOptionsOfProduct(@Param("start") int start, @Param("end") int end, @Param("productId") int productId);

    @Transactional
    @Query(value = "SELECT fo.* " +
            "FROM Feature_Option fo " +
            "WHERE fo.feature_id = :featureId " +
            "ORDER BY fo.id " +
            "LIMIT :start,:end ",
            nativeQuery = true)
    List<FeatureOption> findFeatureOptionsOfFeature(@Param("start") int start, @Param("end") int end, @Param("featureId") int featureId);

}
