package com.demo.rest.controller;

import com.demo.rest.service.NumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/number")
public class NumberController {
    private final NumberService numberService;

    @Autowired
    public NumberController(final NumberService numberService) {
        this.numberService = numberService;
    }

    @PutMapping
    public ResponseEntity<List<Integer>> getNFibonnacci(int number){
        final List<Integer> nFibonnacci = numberService.getNFibonnacci(number);
        return new ResponseEntity<>(nFibonnacci, HttpStatus.OK);
    }
}
