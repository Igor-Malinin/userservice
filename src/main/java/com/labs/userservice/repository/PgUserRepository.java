package com.labs.userservice.repository;

import com.labs.userservice.entity.PgUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PgUserRepository extends JpaRepository<PgUser, String> {
}
