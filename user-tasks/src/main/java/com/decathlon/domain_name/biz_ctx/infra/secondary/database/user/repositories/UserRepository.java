package com.decathlon.domain_name.biz_ctx.infra.secondary.database.user.repositories;

import com.decathlon.domain_name.biz_ctx.infra.secondary.database.user.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
