package com.demo.rest.service;

import com.demo.rest.model.Word;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class WordServiceImplTest {
    private WordServiceImpl wordService;

    @Before
    public void setUp() throws Exception {
        wordService = new WordServiceImpl();
    }

    @Test
    public void calculateWordCount() throws Exception {
        String inputString = "John John Doe is the is Mary Mark David Mark";
        Word word1 = new Word("john", 2);
        Word word2 = new Word("doe", 1);
        Word word3 = new Word("is", 2);
        Word word4 = new Word("the", 1);
        Word word5 = new Word("mary", 1);
        Word word6 = new Word("mark", 2);
        Word word7  = new Word("david", 1);

        List<Word> expectedOutput = Arrays.asList(word7, word2, word3, word1, word6, word5, word4);
        final List<Word> uniqueWordsByCount = wordService.calculateWordCount(inputString);
        assertEquals(expectedOutput, uniqueWordsByCount);

    }

}