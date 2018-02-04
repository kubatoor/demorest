package com.demo.rest.controller;

import com.demo.rest.model.Word;
import com.demo.rest.service.WordService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class WordControllerTest {
    @Mock
    private WordService wordService;
    private MockMvc mockMvc;

    @InjectMocks
    private WordController wordController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(wordController).build();
    }

    @Test
    public void testGetWordCount() throws Exception {
        String inputString = "Mary Mary David David Mark";
        String expectedJson = "[{\"value\":\"David\",\"count\":2},\n" +
                "{\"value\":\"Mary\",\"count\":2},\n" +
                "{\"value\":\"Mark\",\"count\":1}]\n" +
                "}\n";

        String inputJson = "{\"text\":\"Mary Mary David David Mark\"}";

        Word word1 = new Word("Mary",2);
        Word word2 = new Word("David",2);
        Word word3 = new Word("Mark",1);

        when(wordService.calculateWordCount(inputString)).thenReturn(Arrays.asList(word1, word2, word3));

        mockMvc.perform(put("/words").
                contentType(MediaType.APPLICATION_JSON).content(inputJson)).
                andExpect(status().isOk()).andExpect(content()
                .json(expectedJson));
    }

    @Test
    public void testGetWordCount_WithInvalidInput() throws Exception {
        String invalidInputJson = "{\"text\":\"\"}";

        mockMvc.perform(put("/words").
                contentType(MediaType.APPLICATION_JSON).content(invalidInputJson)).
                andExpect(status().isBadRequest());
    }
}