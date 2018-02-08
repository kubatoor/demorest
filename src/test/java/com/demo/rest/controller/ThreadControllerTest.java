package com.demo.rest.controller;

import com.demo.rest.exception.DeadLockCreationException;
import com.demo.rest.service.ThreadService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ThreadControllerTest {
    private MockMvc mockMvc;
    @Mock
    private ThreadService threadService;
    @InjectMocks
    private ThreadController threadController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(threadController).build();
    }

    @Test
    public void createDeadLock() throws Exception {
        when(threadService.createDeadLock()).thenReturn("DeadLock Created");
        final String contentAsString = mockMvc.perform(put("/threads/deadlock")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertEquals("DeadLock Created", contentAsString);
    }

    @Test
    public void createDeadLockWithException() throws Exception {
        doThrow(DeadLockCreationException.class).when(threadService).createDeadLock();
        mockMvc.perform(put("/threads/deadlock")).andExpect(status().isInternalServerError()).andReturn().getResponse().getContentAsString();
    }

    @Test
    public void getDeadLockInfo() throws Exception {
        when(threadService.getDeadLockInfo()).thenReturn("DeadLock Detected");
        final String contentAsString = mockMvc.perform(get("/threads/deadlock")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertEquals("DeadLock Detected",contentAsString);
    }

}