package com.example.demospring.dao

import com.example.demospring.entities.NoticeEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface NoticeDAO {
    NoticeEntity findById(Long id);

    Page<NoticeEntity> findAll(Pageable pageable);

    NoticeEntity save(NoticeEntity entity);
}
