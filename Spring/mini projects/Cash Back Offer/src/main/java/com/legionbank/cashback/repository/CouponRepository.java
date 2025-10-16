package com.legionbank.cashback.repository;

import com.legionbank.cashback.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, String> {
}
