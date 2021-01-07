package com.springboot.SattimSatiyorum.dao;

import com.springboot.SattimSatiyorum.entity.Commercial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

public interface CommercialRepository extends JpaRepository<Commercial, Integer> {

    @Transactional
    @Query(value = "SELECT c.* " +
            "FROM User u inner join Commercial C on u.id = C.seller_id " +
            "WHERE u.id = :id AND c.is_active = 1 " +
            "ORDER BY c.id " +
            "LIMIT :start,:end ", nativeQuery = true)
    List<Commercial> findSoldCommercialsByActiveFromSeller(@Param("start") int start, @Param("end") int end, @Param("id") int id);

    @Transactional
    @Query(value = "SELECT c.* " +
            "FROM User u inner join Commercial C on u.id = C.seller_id " +
            "WHERE u.id = :id AND c.is_active = 0 " +
            "ORDER BY c.id " +
            "LIMIT :start,:end ", nativeQuery = true)
    List<Commercial> findSoldCommercialsByNotActiveFromSeller(@Param("start") int start, @Param("end") int end, @Param("id") int id);


    @Transactional
    @Query(value = "SELECT c.* " +
            "FROM User u inner join Commercial C on u.id = C.buyer_id " +
            "WHERE u.id = :id AND c.is_active = 0 " +
            "ORDER BY c.id " +
            "LIMIT :start,:end ", nativeQuery = true)
    List<Commercial> findBoughtCommercialsFromBuyer(@Param("start") int start, @Param("end") int end, @Param("id") int id);

    @Transactional
    @Query(value = "SELECT c.* " +
            "FROM Commercial c inner join Product p on c.product_id=p.id " +
            "WHERE is_active=1 " +
            "ORDER BY c.id " +
            "LIMIT :start,:end ", nativeQuery = true)
    List<Commercial> findCommercialsByActive(@Param("start") int start, @Param("end") int end);

    @Transactional
    @Query(value = "SELECT c.* " +
            "FROM Commercial c inner join Product p on c.product_id=p.id " +
            "    inner join Product_Feature_Options pfo on p.id = pfo.product_id " +
            "WHERE is_active=1 AND pfo.feature_option_id IN :options " +
            "GROUP BY c.id " +
            "LIMIT :start,:end ", nativeQuery = true)
    List<Commercial> findCommercialsByFeatureOptionIds(@Param("start") int start, @Param("end") int end, @Param("options") List<Integer> featureOptionIds);

    @Transactional
    @Query(value = "SELECT c.* " +
            "FROM Commercial c " +
            "WHERE c.created_at >= :untilDate " +
            "GROUP BY c.id " +
            "LIMIT :start,:end ", nativeQuery = true)
    List<Commercial> findCommercialsByDate(@Param("start") int start, @Param("end") int end, @Param("untilDate") Date date);

    @Transactional
    @Query(value = "SELECT c.* " +
            "FROM Commercial c " +
            "WHERE c.price >= :minPrice AND c.price <= :maxPrice " +
            "GROUP BY c.id " +
            "LIMIT :start,:end ", nativeQuery = true)
    List<Commercial> findCommercialsByPrice(@Param("start") int start, @Param("end") int end, @Param("minPrice") int min, @Param("maxPrice") int max);

    @Transactional
    @Query(value = "SELECT c.* " +
            "FROM Commercial c " +
            "WHERE c.is_urgent = 1" +
            "GROUP BY c.id " +
            "LIMIT :start,:end ", nativeQuery = true)
    List<Commercial> findCommercialsByIsUrgent(@Param("start") int start, @Param("end") int end);


}
