package com.example.demospring.dao

import com.example.demospring.entities.AccountEntity

interface AccountDAO {
    AccountEntity findByUsername(String username);
    AccountEntity save(AccountEntity entity);
}
