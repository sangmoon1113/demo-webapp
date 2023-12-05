package com.example.demospring.daos

import com.example.demospring.entities.NoticeEntity

interface NoticeDAO {
    NoticeEntity findById(Long id);

    List<NoticeEntity> findAll();

    NoticeEntity save(NoticeEntity entity);
}
