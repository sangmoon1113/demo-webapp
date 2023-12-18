package com.example.demospring.dto


import java.time.LocalDateTime

class NoticeResponseDto {
    Long id;
    String title;
    String content;
    LocalDateTime create_date;
    LocalDateTime update_date;
}
