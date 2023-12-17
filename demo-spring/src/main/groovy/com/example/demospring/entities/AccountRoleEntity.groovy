package com.example.demospring.entities

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "account_role")
class AccountRoleEntity {
    @Id
    long accountId;

    @Id
    long roleId;
}
