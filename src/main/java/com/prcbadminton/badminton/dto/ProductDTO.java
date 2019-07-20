package com.prcbadminton.badminton.dto;

import com.prcbadminton.badminton.entities.Image;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.List;

public class ProductDTO implements Serializable {
    private int id;
    private String name;
    private double price;
    private String description;
    private int quantity;
    private String flex;
    private String shaft;
    private String weight;
    private String color;
    private int producerId;
    private int promotionId;
    private List<Image> image;

    public ProductDTO() {
    }

    public ProductDTO(int id, String name, double price, String description, int quantity, String flex, String shaft, String weight, String color, int producerId, int promotionId, List<Image> image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.flex = flex;
        this.shaft = shaft;
        this.weight = weight;
        this.color = color;
        this.producerId = producerId;
        this.promotionId = promotionId;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public ProductDTO setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductDTO setName(String name) {
        this.name = name;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public ProductDTO setPrice(double price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public ProductDTO setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getFlex() {
        return flex;
    }

    public ProductDTO setFlex(String flex) {
        this.flex = flex;
        return this;
    }

    public String getShaft() {
        return shaft;
    }

    public ProductDTO setShaft(String shaft) {
        this.shaft = shaft;
        return this;
    }

    public String getWeight() {
        return weight;
    }

    public ProductDTO setWeight(String weight) {
        this.weight = weight;
        return this;
    }

    public String getColor() {
        return color;
    }

    public ProductDTO setColor(String color) {
        this.color = color;
        return this;
    }

    public int getProducerId() {
        return producerId;
    }

    public ProductDTO setProducerId(int producerId) {
        this.producerId = producerId;
        return this;
    }

    public int getPromotionId() {
        return promotionId;
    }

    public ProductDTO setPromotionId(int promotionId) {
        this.promotionId = promotionId;
        return this;
    }

    public List<Image> getImage() {
        return image;
    }

    public ProductDTO setImage(List<Image> image) {
        this.image = image;
        return this;
    }
}
