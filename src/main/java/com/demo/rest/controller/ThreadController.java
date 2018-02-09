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

    /**
     * controller method for creating deadlock in the system. This method is idempotent in the sense that if the system
     * already has an existing deadlock then a new DeadLock is not created. Instead it just reports the existing deadlock information.
     *
     * @return Deadlock Creation information ResponseEntity
     */
    @PutMapping("/deadlock")
    public ResponseEntity<String> createDeadLock() {
        final String deadLock = threadService.createDeadLock();
        return new ResponseEntity<>(deadLock, HttpStatus.OK);
    }

    /**
     * controller method for getting deadlock information from the system
     * @return Deadlock information ResponseEntity
     */
    @GetMapping("/deadlock")
    public ResponseEntity<String> getDeadLockInfo(){
        String deadLockInfo = threadService.getDeadLockInfo();
        return new ResponseEntity<>(deadLockInfo, HttpStatus.OK);
    }
}
