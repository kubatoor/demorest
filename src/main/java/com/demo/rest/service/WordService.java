package com.demo.rest.service;


import com.demo.rest.model.Word;

import java.util.List;

/**
 * Service layer backing the words controller
 */
public interface WordService {

    /**
     * This method takes in a long paragraph of Text and returns the unique words in the Text along with
     * the count of each value.
     *
     * @param text
     * @return list of Word objects. Each Word depicts the unique value in input text and its count.
     * @throws IllegalArgumentException for null or empty input
     */
    List<Word> calculateWordCount(String text);
}
