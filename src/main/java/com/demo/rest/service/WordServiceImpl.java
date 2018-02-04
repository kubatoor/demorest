package com.demo.rest.service;

import com.demo.rest.model.Word;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WordServiceImpl implements WordService {

    /**
     * This method uses TreeMap to sort the words lexicographically
     * @param text
     * @return list of Word where each Word represents word and its count
     */

    @Override
    public List<Word> calculateWordCount(final String text) {

        final String[] words = text.toLowerCase().split(" ");
        List<Word> uniqueWordCountList = new ArrayList<>();
        Map<String, Integer> uniqueWordsMap = new TreeMap<>();
        for (String word : words) {
            if(uniqueWordsMap.containsKey(word)){
                int count = uniqueWordsMap.get(word);
                uniqueWordsMap.put(word, count+1);
            }else{
                uniqueWordsMap.put(word, 1);
            }
        }
        for(Map.Entry<String, Integer> entries : uniqueWordsMap.entrySet()){
            uniqueWordCountList.add(new Word(entries.getKey(), entries.getValue()));
        }

        return uniqueWordCountList;
    }
}
