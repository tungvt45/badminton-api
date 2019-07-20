package com.prcbadminton.badminton.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Orders")
@Getter
@Setter
public class Order implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;
    @NotNull
    @Column(name = "date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date date = new Date();

    public Order() {
    }

    public Order(@NotNull User user_id, @NotNull Date date) {
        this.user_id = user_id;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
