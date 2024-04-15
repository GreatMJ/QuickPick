package com.example.QuickPick.repository;

import com.example.QuickPick.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Integer> {
    public Driver findByMobNo(String mobNo);

  //  public boolean existsByMobNo(String mobNo);
}
