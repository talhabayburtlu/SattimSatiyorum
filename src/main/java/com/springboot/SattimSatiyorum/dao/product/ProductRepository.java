package com.springboot.SattimSatiyorum.dao.product;

import com.springboot.SattimSatiyorum.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductRepository<T extends Product> extends JpaRepository<T, Integer> {

    @Transactional
    @Query(value = "SELECT * " +
            "FROM Product p " +
            "WHERE LOWER(p.header) LIKE CONCAT('%',:subHeader, '%') AND p.type = :type " +
            "ORDER BY p.id " +
            "LIMIT :start,:end ", nativeQuery = true)
    List<T> findProductByHeader(@Param("start") int start, @Param("end") int end, @Param("type") String type, @Param("subHeader") String subHeader);

}
