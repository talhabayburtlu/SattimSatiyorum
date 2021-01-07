package com.springboot.SattimSatiyorum.dao;

import com.springboot.SattimSatiyorum.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface PackageRepository extends JpaRepository<Package, Integer> {

    @Transactional
    @Query(value = "SELECT p.* " +
            "FROM User u inner join Package p on u.package_id = p.id " +
            "WHERE package_id = :id ", nativeQuery = true)
    Package findPackageOfUser(@Param("id") int id);

}
