package com.architecture.java.cleanarchi.repositories;

import com.architecture.java.cleanarchi.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
