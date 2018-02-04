package com.demo.rest.controller;

import com.demo.rest.model.InputMessage;
import com.demo.rest.model.Word;
import com.demo.rest.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WordController {

    @Autowired
    private WordService wordService;

    @PutMapping("/wordcount")
    public ResponseEntity<List<Word>> getWordCount(@RequestBody InputMessage inputMessage) {
        final String text = inputMessage.getText();
        final List<Word> wordCount = wordService.calculateWordCount(text);
        return new ResponseEntity<>(wordCount, HttpStatus.OK);
    }

}
