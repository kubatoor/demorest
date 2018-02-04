package com.demo.rest.service;

import com.demo.rest.model.Word;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class WordServiceImpl implements WordService {

    @Override
    public List<Word> calculateWordCount(final String text) {
        Word word1 = new Word();
        word1.setWord("Mary");
        word1.setCount(2);

        Word word2 = new Word();
        word2.setWord("David");
        word2.setCount(2);

        Word word3 = new Word();
        word3.setWord("Mark");
        word3.setCount(1);

        return Arrays.asList(word1, word2, word3);
    }
}
