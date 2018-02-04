package com.demo.rest.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit test for the HelloWorldController
 */
public class HelloWorldControllerTest {

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new HelloWorldController()).build();
    }

    @Test
    public void testHelloWorldController() throws Exception {
        mockMvc.perform(get("/helloworld")).andExpect(status().isOk())
                .andExpect(content().string("Hello World"));

    }

    @Test
    public void testHelloWorldController_NotFound() throws Exception {
        mockMvc.perform(get("/helloworlde")).andExpect(status().isNotFound());

    }
}