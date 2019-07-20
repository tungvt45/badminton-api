package com.prcbadminton.badminton.services;

import com.prcbadminton.badminton.entities.User;
import com.prcbadminton.badminton.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{
    @Autowired
    private UserRepository userRepository;


    @Override
    public boolean signUp(User user) {
        // check existed
        Optional<User> userCheckExisted = userRepository.findByEmail(user.getEmail());
        if (userCheckExisted.isPresent()) {
            return false;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = user.getPassword();
        password = encoder.encode(password);
        user.setPassword(password);

        int id = userRepository.save(user).getId();
        if (id > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<User> getAll() throws Exception {
        return userRepository.getAllByRoleNotLike("ROLE_ADMIN");
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }


}
