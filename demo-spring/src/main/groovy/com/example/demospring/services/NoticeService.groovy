package com.example.demospring.services

import com.example.demospring.dto.NoticePagingResponseDto
import com.example.demospring.dto.NoticeResponseDto
import com.example.demospring.dto.NoticeRequestDto
import org.springframework.data.domain.Pageable

interface NoticeService {
    NoticeResponseDto findById(Long id);

    NoticePagingResponseDto findAll(Pageable pageable);

    NoticeResponseDto save(NoticeRequestDto requestDto);

    void save(Long id, NoticeRequestDto requestDto);
}