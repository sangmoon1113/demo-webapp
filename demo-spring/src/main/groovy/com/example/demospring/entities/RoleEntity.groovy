package com.example.demospring.entities

import jakarta.persistence.*

@Entity
@Table(name = "role")
class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
}
