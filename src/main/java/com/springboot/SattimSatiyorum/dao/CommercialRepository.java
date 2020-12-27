package com.springboot.SattimSatiyorum.dao;

import com.springboot.SattimSatiyorum.entity.Commercial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CommercialRepository extends JpaRepository<Commercial, Integer> {


    @Transactional
    @Query(value = "SELECT * " +
            "FROM Commercial " +
            "WHERE is_active=1 " +
            "ORDER BY id " +
            "LIMIT :start,:end ", nativeQuery = true)
    List<Commercial> findCommercialsByActive(@Param("start") int start, @Param("end") int end);


}
