package com.prcbadminton.badminton.repository;

import com.prcbadminton.badminton.entities.Image;
import com.prcbadminton.badminton.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    List<Image> findAllByProduct(Product product);
}
