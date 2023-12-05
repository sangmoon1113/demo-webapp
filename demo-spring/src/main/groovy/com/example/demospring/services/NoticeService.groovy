package com.example.demospring.services

import com.example.demospring.dtos.NoticeDto
import com.example.demospring.dtos.NoticeRequestDto

interface NoticeService {
    NoticeDto findById(Long id);

    List<NoticeDto> findAll();

    NoticeDto save(NoticeRequestDto requestDto);

    void save(Long id, NoticeRequestDto requestDto);
}