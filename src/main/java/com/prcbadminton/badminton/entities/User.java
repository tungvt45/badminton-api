package com.prcbadminton.badminton.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "Users")
@Getter
@Setter
public class User implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Column(name = "name")
    private String name;
    @NotNull
    @Column(name = "password")
    private String password;
    @NotNull
    @Column(name = "email")
    private String email;
    @NotNull
    @Column(name = "role")
    private String role;
    @NotNull
    @Column(name = "active")
    private boolean active;
    @NotNull
    @Column(name = "address")
    private String address;
    public User() {
    }

    public User(@NotNull String name, @NotNull String password, @NotNull String email, @NotNull String role, @NotNull boolean active, @NotNull String address) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
        this.active = active;
        this.address = address;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
