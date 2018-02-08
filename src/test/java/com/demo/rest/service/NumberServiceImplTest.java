package com.demo.rest.service;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class NumberServiceImplTest {
    private NumberServiceImpl numberService;

    @Before
    public void setUp() {
        numberService = new NumberServiceImpl();
    }

    @Test
    public void getNFibonnacci() throws Exception {
        List<Long> expectedList = Arrays.asList(0L,1L,1L,2L,3L,5L,8L,13L,21L,34L);
        int maxCount = 10;
        final List<Long> actualList = numberService.getNFibonnacci(maxCount);
        assertEquals(expectedList, actualList);
    }

    @Test
    public void getNFibonnacci_WithZeroMaxCount() throws Exception {
        List<Long> expectedList = new ArrayList<>();
        int maxCount = 0;
        final List<Long> actualList = numberService.getNFibonnacci(maxCount);
        assertEquals(expectedList, actualList);
    }

    @Test
    public void getNFibonnacci_WithOneMaxCount() throws Exception {
        List<Long> expectedList = Collections.singletonList(0L);
        int maxCount = 1;
        final List<Long> actualList = numberService.getNFibonnacci(maxCount);
        assertEquals(expectedList, actualList);
    }

    @Test
    public void getNFibonnacci_WithTwoMaxCount() throws Exception {
        List<Long> expectedList = Arrays.asList(0L, 1L);
        int maxCount = 2;
        final List<Long> actualList = numberService.getNFibonnacci(maxCount);
        assertEquals(expectedList, actualList);
    }

}