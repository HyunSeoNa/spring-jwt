package com.example.springjwt.repository;

import com.example.springjwt.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * fileName     : null.java
 * author       : hyunseo
 * date         : 2025. 3. 19.
 * description  :
 */
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Boolean existsByUsername(String username);
}
