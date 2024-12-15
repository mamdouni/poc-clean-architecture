package com.architecture.java.hexa.repositories;

import com.architecture.java.hexa.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
