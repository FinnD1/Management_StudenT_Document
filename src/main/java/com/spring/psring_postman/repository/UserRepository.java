package com.spring.psring_postman.repository;

import com.spring.psring_postman.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    boolean existsByUserName(String username);
    Optional<User> findByUserName(String userName);
}
