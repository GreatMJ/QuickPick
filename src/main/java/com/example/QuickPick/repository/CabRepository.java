package com.example.QuickPick.repository;

import com.example.QuickPick.models.Cab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CabRepository extends JpaRepository<Cab,Integer> {

    @Query(value = "select * from cab where available=true order by rand() limit 1",nativeQuery = true)
    public Cab getAvailableCab();
}
