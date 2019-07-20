package com.prcbadminton.badminton.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "bestSales")
public class BestSales implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Column(name = "quantity")
    private double quantity;
    @NotNull
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product_id;

    public BestSales() {
    }

    public BestSales(@NotNull double quantity, @NotNull Product product_id) {
        this.quantity = quantity;
        this.product_id = product_id;
    }

    public int getId() {
        return id;
    }

    public BestSales setId(int id) {
        this.id = id;
        return this;
    }

    public double getQuantity() {
        return quantity;
    }

    public BestSales setQuantity(double quantity) {
        this.quantity = quantity;
        return this;
    }

    public Product getProduct_id() {
        return product_id;
    }

    public BestSales setProduct_id(Product product_id) {
        this.product_id = product_id;
        return this;
    }
}
