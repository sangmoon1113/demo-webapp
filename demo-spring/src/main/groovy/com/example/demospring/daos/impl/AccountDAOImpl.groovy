package com.example.demospring.daos.impl

import com.example.demospring.daos.AccountDAO
import com.example.demospring.entities.AccountEntity
import com.example.demospring.repositories.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class AccountDAOImpl implements AccountDAO {
    private final AccountRepository repository;

    AccountDAOImpl (@Autowired(required=true) AccountRepository repository) {
        this.repository = repository;
    }

    AccountEntity findByUsername(String username) {
        return repository.getByUsername(username);
    }

    AccountEntity save(AccountEntity entity) {
        return repository.save(entity)
    }
}
