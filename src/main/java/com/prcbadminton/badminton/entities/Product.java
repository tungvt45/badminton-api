package com.prcbadminton.badminton.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Product")
@Getter
@Setter
public class Product implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Column(name = "name")
    private String name;
    @NotNull
    @Column(name = "price")
    private double price;
    @NotNull
    @Column(name = "description")
    private String description;
    @NotNull
    @Column(name = "flex")
    private String flex;
    @NotNull
    @Column(name = "shaft")
    private String shaft;
    @NotNull
    @Column(name = "weight")
    private String weight;
    @NotNull
    @Column(name = "color")
    private String color;
    @JsonManagedReference
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<Image> image;
    @NotNull
    @OneToOne
    @JoinColumn(name = "producer_id")
    private Producer producer;
    @OneToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;
    public Product() {
    }

    public Product(@NotNull String name, @NotNull double price, @NotNull String description, @NotNull String flex, @NotNull String shaft, @NotNull String weight, @NotNull String color, Set<Image> image, @NotNull Producer producer, Promotion promotion) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.flex = flex;
        this.shaft = shaft;
        this.weight = weight;
        this.color = color;
        this.image = image;
        this.producer = producer;
        this.promotion = promotion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFlex() {
        return flex;
    }

    public void setFlex(String flex) {
        this.flex = flex;
    }

    public String getShaft() {
        return shaft;
    }

    public void setShaft(String shaft) {
        this.shaft = shaft;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Set<Image> getImage() {
        return image;
    }

    public void setImage(Set<Image> image) {
        this.image = image;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }
}
