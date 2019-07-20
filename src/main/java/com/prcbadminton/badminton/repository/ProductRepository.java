package com.prcbadminton.badminton.repository;

import com.prcbadminton.badminton.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.websocket.server.PathParam;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "SELECT * from product p where p.promotion_id is not null order by price asc limit 9", nativeQuery = true)
    public List<Product> getProductInHomePage();

    @Query(value = "SELECT p.id, p.color, p.description, p.flex, p.name, p.price, p.producer_id, p.promotion_id, p.weight, p.shaft from product p, best_sales bs where p.id = bs.product_id order by bs.quantity desc limit 9", nativeQuery = true)
    public List<Product> getBestSalesProduct();

    @Query(value = "SELECT * from product p order by p.id asc limit 9", nativeQuery = true)
    public List<Product> getFourProduct();

    @Query(value = "SELECT * from product p where p.name like %:searchValue% order by p.id asc", nativeQuery = true)
    public Page<Product> getProductByName(Pageable pageable, @Param("searchValue") String searchValue);


    @Query(value = "SELECT * from product p where p.promotion_id is not null order by price asc", nativeQuery = true)
    public Page<Product> getAllPromotionProduct(Pageable pageable);

    @Query(value = "SELECT p.id, p.active, p.color, p.description, p.flex,  p.name, p.price, p.producer_id, p.quantity, p.promotion_id, p.weight, p.shaft from product p, best_sales bs where p.id = bs.product_id order by bs.quantity desc", nativeQuery = true)
    public Page<Product> getAllBestSalesProduct(Pageable pageable);


//    @Query(value = "Select p from Product p, BestSales bs  where p.promotion is not null and p.id = bs.product_id order by p.price, bs.quantity")
//    public List<Product> getViewAllProduct();
}
