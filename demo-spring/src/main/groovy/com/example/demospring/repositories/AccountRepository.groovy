package com.example.demospring.repositories

import com.example.demospring.entities.AccountEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    AccountEntity getByUsername(String username);
}
