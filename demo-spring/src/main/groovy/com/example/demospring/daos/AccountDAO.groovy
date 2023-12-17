package com.example.demospring.daos

import com.example.demospring.entities.AccountEntity

interface AccountDAO {
    AccountEntity findByUsername(String username);
    AccountEntity save(AccountEntity entity);
}
