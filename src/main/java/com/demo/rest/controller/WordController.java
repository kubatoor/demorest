package com.demo.rest.controller;

import com.demo.rest.model.InputMessage;
import com.demo.rest.model.Word;
import com.demo.rest.service.WordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller which serves the /words endpoint.
 */

@RestController
@RequestMapping("/words")
public class WordController {

    private final WordService wordService;
    private Logger LOG = LoggerFactory.getLogger(WordController.class);

    @Autowired
    public WordController(final WordService wordService) {
        this.wordService = wordService;
    }

    /**
     * The method is a controller method which takes in an input String and outputs
     * the unique words along with its count as the REST response.
     *
     * @param inputMessage
     * @return
     */

    @PutMapping
    public ResponseEntity<List<Word>> getUniqueWordCount(@RequestBody @Valid InputMessage inputMessage) {
        LOG.debug("Input Text="+inputMessage.getText());
        final String text = inputMessage.getText();
        final List<Word> wordCount = wordService.calculateWordCount(text);
        return new ResponseEntity<>(wordCount, HttpStatus.OK);
    }

}
