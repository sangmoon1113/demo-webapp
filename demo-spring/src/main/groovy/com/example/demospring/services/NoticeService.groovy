package com.example.demospring.services

import com.example.demospring.dtos.NoticePagingResponseDto
import com.example.demospring.dtos.NoticeResponseDto
import com.example.demospring.dtos.NoticeRequestDto
import org.springframework.data.domain.Pageable

interface NoticeService {
    NoticeResponseDto findById(Long id);

    NoticePagingResponseDto findAll(Pageable pageable);

    NoticeResponseDto save(NoticeRequestDto requestDto);

    void save(Long id, NoticeRequestDto requestDto);
}