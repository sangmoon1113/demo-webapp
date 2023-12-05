package com.example.demospring.entities

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "notice")
class NoticeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "title", length = 100, nullable = false)
    String title;

    @Column(name = "content", columnDefinition = "TEXT")
    String content;

    @CreatedDate
    @Column(name = "create_date", updatable = false)
    LocalDateTime createDate;

    @LastModifiedDate
    @Column(name = "update_date")
    LocalDateTime updateDate;
}
