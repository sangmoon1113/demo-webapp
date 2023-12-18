package com.example.demospring.dao.impl

import com.example.demospring.dao.NoticeDAO
import com.example.demospring.entities.NoticeEntity
import com.example.demospring.repositories.NoticeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
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
    Page<NoticeEntity> findAll(Pageable pageable) {
        def p =  repository.findAllByOrderByIdDesc(pageable);
    }

    @Override
    NoticeEntity save(NoticeEntity entity) {
        return repository.save(entity)
    }
}
