package com.demo.rest.controller;

import com.demo.rest.service.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/threads")
public class ThreadController {

    private final ThreadService threadService;

    @Autowired
    public ThreadController(final ThreadService threadService) {
        this.threadService = threadService;
    }

    @PutMapping("/deadlock")
    public ResponseEntity<String> createDeadLock() {
        final String deadLock = threadService.createDeadLock();
        return new ResponseEntity<>(deadLock, HttpStatus.OK);
    }

    @GetMapping("/deadlock")
    public ResponseEntity<String> getDeadLockInfo(){
        String deadLockInfo = threadService.getDeadLockInfo();
        return new ResponseEntity<>(deadLockInfo, HttpStatus.OK);
    }
}
