package com.demo.rest.service;


import com.demo.rest.model.Word;

import java.util.List;

public interface WordService {

    List<Word> calculateWordCount(String text);
}
