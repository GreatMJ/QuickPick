package com.example.QuickPick.repository;

import com.example.QuickPick.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Integer> {
     Driver findByMobNo(String mobNo);

     @Query(value = "select * from driver where age >= :age", nativeQuery = true)
     List<Driver> getDriverWithAgeGreaterThan(@Param("age") Integer age);

}
