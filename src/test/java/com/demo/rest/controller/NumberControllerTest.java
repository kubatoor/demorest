package com.demo.rest.controller;

import com.demo.rest.service.NumberService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class NumberControllerTest {

    private MockMvc mockMvc;
    @Mock
    private NumberService numberService;
    @InjectMocks
    private NumberController numberController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(numberController).build();
    }

    @Test
    public void getNFibonnacci() throws Exception {
        int maxCount = 9;
        String expectedJson = "[0,1,1,2,3,5,8,13,21]";
        when(numberService.getNFibonnacci(maxCount)).thenReturn(Arrays.asList(0L, 1L, 1L, 2L, 3L, 5L, 8L, 13L, 21L));
        mockMvc.perform(get("/numbers/fibonacci/" + maxCount)).andExpect(status().isOk()).andExpect(content().json(expectedJson));
    }

    @Test
    public void getNFibonnacci_WithZeroMaxCount() throws Exception {
        int maxCount = 0;
        String expectedJson = "[]";
        when(numberService.getNFibonnacci(maxCount)).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/numbers/fibonacci/" + maxCount)).andExpect(status().isOk()).andExpect(content().json(expectedJson));
    }

    @Test
    public void getNFibonnacci_WithOutInput() throws Exception {
        mockMvc.perform(get("/numbers/fibonacci/")).andExpect(status().isNotFound());
    }

}