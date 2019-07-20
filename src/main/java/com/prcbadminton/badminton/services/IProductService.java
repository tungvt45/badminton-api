package com.prcbadminton.badminton.services;

import com.prcbadminton.badminton.entities.Product;

import java.util.List;

public interface IProductService {
    public List<Product> getProductInHomePage();
    public List<Product> getBestSalesProduct();
    public List<Product> getFourProduct();
}
