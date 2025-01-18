package com.developer.library.repository;

import com.developer.library.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users,Integer>{
    Optional<Users> findByEmail(String email);
}
