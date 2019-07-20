package com.prcbadminton.badminton.dto;

import java.util.List;

public class OrderDTO {
    private int id;
    private int count;
    private float price;

    public OrderDTO(int id, int count, float price) {
        this.id = id;
        this.count = count;
        this.price = price;
    }

    public OrderDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
