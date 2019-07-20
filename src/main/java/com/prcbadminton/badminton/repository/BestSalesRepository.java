package com.prcbadminton.badminton.repository;

import com.prcbadminton.badminton.entities.BestSales;
import com.prcbadminton.badminton.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BestSalesRepository extends JpaRepository<BestSales, Integer> {
    @Query(value = "select * from best_sales as bs where bs.product_id = :productId", nativeQuery = true)
    Optional<BestSales> findBestSalesByProduct(@Param("productId") int productId);
}
