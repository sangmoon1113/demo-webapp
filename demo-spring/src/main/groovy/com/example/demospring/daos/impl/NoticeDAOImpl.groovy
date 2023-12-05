package com.example.demospring.daos.impl

import com.example.demospring.daos.NoticeDAO
import com.example.demospring.entities.NoticeEntity
import com.example.demospring.repositories.NoticeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class NoticeDAOImpl implements NoticeDAO{
    private final NoticeRepository repository;

    NoticeDAOImpl(@Autowired(required=true) NoticeRepository repository) {
        this.repository = repository;
    }

    @Override
    NoticeEntity findById(Long id) {
        def optional =  repository.findById(id);
        if(optional.isPresent()) {
            return optional.get();
        } else {
            return optional.orElse(null);
        }
    }

    @Override
    List<NoticeEntity> findAll() {
        return repository.findAll();
    }

    @Override
    NoticeEntity save(NoticeEntity entity) {
        return repository.save(entity)
    }
}
