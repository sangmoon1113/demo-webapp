package com.example.demospring.controllers

import com.example.demospring.dtos.NoticeDto
import com.example.demospring.dtos.NoticeRequestDto
import com.example.demospring.services.NoticeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/notice")
class NoticeController {

    final NoticeService noticeService;

    @Autowired
    NoticeController(NoticeService noticeService ) {
        this.noticeService = noticeService;
    }

    @GetMapping
    List<NoticeDto> findAll() {
        return noticeService.findAll();
    }

    @GetMapping(value="/{id}")
    ResponseEntity<NoticeDto> findById(@PathVariable(value = "id") Long id) {
        def responseDto = noticeService.findById(id);
        if(responseDto != null) {
            return new ResponseEntity<NoticeDto>(responseDto, HttpStatus.OK);
        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .body(Map.of("message", "Resource not found"));
            return new ResponseEntity(Map.of("message", "Resource not found"), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    ResponseEntity<NoticeDto> insert(@RequestBody NoticeRequestDto requestDto) {
        def responseDto = noticeService.save(requestDto);
        if(responseDto != null) {
            return new ResponseEntity<NoticeDto>(responseDto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<NoticeDto>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}")
    ResponseEntity update(@PathVariable(value = "id") Long id, @RequestBody NoticeRequestDto requestDto) {
        noticeService.save(id, requestDto);

        return new ResponseEntity(null, HttpStatus.NO_CONTENT);
    }
}
