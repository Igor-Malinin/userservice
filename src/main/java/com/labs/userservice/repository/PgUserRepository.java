package com.labs.userservice.repository;

import com.labs.userservice.entity.PgUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PgUserRepository extends JpaRepository<PgUser, String> {
    List<PgUser> getUsersByCompanyId(String id);
}
