package com.example.demospring.controllers

import com.example.demospring.dtos.NoticePagingResponseDto
import com.example.demospring.dtos.NoticeResponseDto
import com.example.demospring.dtos.NoticeRequestDto
import com.example.demospring.services.NoticeService
import io.swagger.v3.oas.annotations.Parameter
import org.springdoc.core.converters.models.PageableAsQueryParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/notice")
class NoticeController {

    final NoticeService noticeService;

    @Autowired
    NoticeController(NoticeService noticeService ) {
        this.noticeService = noticeService;
    }

    @GetMapping
    @PageableAsQueryParam
    NoticePagingResponseDto findAll(@Parameter(hidden = true) @PageableDefault(page = 0, size = 20) Pageable pageable) {
        return noticeService.findAll(pageable);
    }

    @GetMapping(value="/{id}")
    ResponseEntity<NoticeResponseDto> findById(@PathVariable(value = "id") Long id) {
        def responseDto = noticeService.findById(id);
        if(responseDto != null) {
            return new ResponseEntity<NoticeResponseDto>(responseDto, HttpStatus.OK);
        } else {
            return new ResponseEntity(Map.of("message", "Resource not found"), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    ResponseEntity<NoticeResponseDto> insert(@RequestBody NoticeRequestDto requestDto) {
        def responseDto = noticeService.save(requestDto);
        if(responseDto != null) {
            return new ResponseEntity<NoticeResponseDto>(responseDto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<NoticeResponseDto>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}")
    ResponseEntity update(@PathVariable(value = "id") Long id, @RequestBody NoticeRequestDto requestDto) {
        noticeService.save(id, requestDto);

        return new ResponseEntity(null, HttpStatus.NO_CONTENT);
    }
}
