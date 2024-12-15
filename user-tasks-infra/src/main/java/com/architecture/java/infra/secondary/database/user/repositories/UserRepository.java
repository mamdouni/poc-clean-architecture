package com.architecture.java.infra.secondary.database.user.repositories;

import com.architecture.java.infra.secondary.database.user.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
