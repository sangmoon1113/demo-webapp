package com.example.demospring.services.impl

import com.example.demospring.daos.NoticeDAO
import com.example.demospring.dtos.NoticeDto
import com.example.demospring.dtos.NoticeRequestDto
import com.example.demospring.entities.NoticeEntity
import com.example.demospring.services.NoticeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class NoticeServiceImpl implements NoticeService {

    final NoticeDAO dao;

    @Autowired
    NoticeServiceImpl(NoticeDAO dao) {
        this.dao = dao;
    }

    NoticeDto findById(Long id) {
        def entity = dao.findById(id)
        if(entity != null) {
            return new NoticeDto(
                    id: entity.id,
                    title: entity.title,
                    content: entity.content,
                    create_date: entity.createDate,
                    update_date: entity.updateDate,
            );
        } else {
            return null;
        }
    }

    List<NoticeDto> findAll() {
        def responseDto = new ArrayList<NoticeDto>();
        def entities = dao.findAll();
        for(def entity in entities) {
            responseDto.add(new NoticeDto(
                id: entity.id,
                title: entity.title,
                content: entity.content,
                create_date: entity.createDate,
                update_date: entity.updateDate,
            ));
        }

        return responseDto;
    }

    NoticeDto save(NoticeRequestDto requestDto) {
        def entity = new NoticeEntity(
                title: requestDto.title,
                content: requestDto.content,
        );
        def resultEntity = dao.save(entity)
        return new NoticeDto(
                id: entity.id,
                title: entity.title,
                content: entity.content,
                create_date: entity.createDate,
                update_date: entity.updateDate,
        );
    }

    void save(Long id, NoticeRequestDto requestDto) {
        def entity = new NoticeEntity(
                id: id,
                title: requestDto.title,
                content: requestDto.content,
        );
        dao.save(entity)
    }
}
