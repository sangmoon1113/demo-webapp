package com.example.demospring.services.impl

import com.example.demospring.daos.NoticeDAO
import com.example.demospring.dtos.NoticePagingResponseDto
import com.example.demospring.dtos.NoticeResponseDto
import com.example.demospring.dtos.NoticeRequestDto
import com.example.demospring.entities.NoticeEntity
import com.example.demospring.services.NoticeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class NoticeServiceImpl implements NoticeService {

    final NoticeDAO dao;

    @Autowired
    NoticeServiceImpl(NoticeDAO dao) {
        this.dao = dao;
    }

    NoticeResponseDto findById(Long id) {
        def entity = dao.findById(id)
        if(entity != null) {
            return new NoticeResponseDto(
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

    NoticePagingResponseDto findAll(Pageable pageable) {
        def responseDto = new NoticePagingResponseDto();
        def page = dao.findAll(pageable);
        for(def entity in page.content) {
            responseDto.items.add(new NoticeResponseDto(
                id: entity.id,
                title: entity.title,
                content: entity.content,
                create_date: entity.createDate,
                update_date: entity.updateDate,
            ));
        }



        def pageCount = 5;
        responseDto.total = page.totalElements;
        responseDto.pageCount = page.totalPages;
        responseDto.endPage = (int)(Math.ceil((pageable.getPageNumber()+1)*1.0/pageCount))*pageCount;
        responseDto.startPage = responseDto.endPage - (pageCount-1);
        responseDto.realEnd = (int)(Math.ceil(responseDto.total*1.0/pageable.getPageSize()));
        if(responseDto.endPage > responseDto.realEnd) {
            responseDto.endPage = responseDto.realEnd == 0 ? 1 : responseDto.realEnd;
        }
        responseDto.prev = responseDto.startPage > 1;
        responseDto.next = responseDto.endPage < responseDto.realEnd;

        return responseDto;
    }

    NoticeResponseDto save(NoticeRequestDto requestDto) {
        def entity = new NoticeEntity(
                title: requestDto.title,
                content: requestDto.content,
        );
        def resultEntity = dao.save(entity)
        return new NoticeResponseDto(
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
