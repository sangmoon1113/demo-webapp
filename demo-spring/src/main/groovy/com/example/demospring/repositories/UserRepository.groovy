package com.example.demospring.repositories

import com.example.demospring.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity getByUsername(String username);
}
