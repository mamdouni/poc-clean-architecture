package com.architecture.java.layered.repositories;

import com.architecture.java.layered.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
