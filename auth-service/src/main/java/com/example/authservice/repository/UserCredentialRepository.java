package com.example.authservice.repository;

import com.example.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface UserCredentialRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String username);
}
