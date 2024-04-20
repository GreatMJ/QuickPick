package com.example.QuickPick.repository;

import com.example.QuickPick.models.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon,Integer> {

    @Query(value = "select * from coupon order by rand() limit 1",nativeQuery = true)
    public Coupon getRandomCoupon();
}
