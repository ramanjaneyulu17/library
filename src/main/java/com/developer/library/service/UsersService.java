package com.developer.library.service;

import com.developer.library.model.Users;
import com.developer.library.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private UserRepo repo;

    public List<Users> getAllUsers() {
        return repo.findAll();
    }

    public Optional<Users> findById(int id) {
        return repo.findById(id);
    }

    public void addUser(Users users) {
        repo.save(users);
    }

    public void updateUser(int id, Users users) {
        users.setId(id);
        repo.save(users);
    }

    public void deleteUser(int id) {
        repo.deleteById(id);
    }

    public Optional<Users> findByEmail(String email) {
        return  repo.findByEmail(email);
    }
}
