package com.example.T2507e.repository;

import com.example.T2507e.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String Email);
}
