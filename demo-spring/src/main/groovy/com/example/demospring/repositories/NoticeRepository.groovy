package com.example.demospring.repositories

import com.example.demospring.entities.NoticeEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface NoticeRepository extends JpaRepository<NoticeEntity, Long> {
    Page<NoticeEntity> findAllByOrderByIdDesc(Pageable pageable);
}
