package com.demo.rest.controller;

import com.demo.rest.service.NumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/numbers")
public class NumberController {
    private final NumberService numberService;

    @Autowired
    public NumberController(final NumberService numberService) {
        this.numberService = numberService;
    }

    /**
     * Controller method that provides a list of N fibonacci numbers
     *
     * @param maxCount the number N required for generating N fibonacci numbers
     * @return a ResponseEnity object containing the list N fibonacci numbers
     */
    @GetMapping("/fibonacci/{maxcount}")
    public ResponseEntity<List<Long>> getNFibonnacci(@PathVariable("maxcount") int maxCount){
        final List<Long> nFibonnacci = numberService.getNFibonnacci(maxCount);
        return new ResponseEntity<>(nFibonnacci, HttpStatus.OK);
    }
}
