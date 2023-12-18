package com.example.demospring.dto

class NoticePagingResponseDto {
    Long total;
    int pageCount;
    int startPage;
    int endPage;
    int realEnd;
    Boolean prev;
    Boolean next;
    List<NoticeResponseDto> items = new ArrayList<NoticeResponseDto>();
}
