package com.prcbadminton.badminton.services;

import com.prcbadminton.badminton.entities.User;

import java.util.List;

public interface IUserService {
    boolean signUp(User user);
    List<User> getAll() throws Exception;
    void save(User user);
}
