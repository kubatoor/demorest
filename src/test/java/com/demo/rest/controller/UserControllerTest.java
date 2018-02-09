package com.demo.rest.controller;

import com.demo.rest.entity.User;
import com.demo.rest.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest {
    private MockMvc mockMvc;
    @Mock
    private UserService userService;
    @InjectMocks
    private UserController userController;

    private static final String BASE_URL = "/users";

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void getUserById() throws Exception {
        String userId = "jdoe";
        String email = "jdoe@gmail.com";
        String name = "John Doe";

        when(userService.getUserById(userId)).thenReturn(getMockUsers().get(0));

        final String userJson = mockMvc.perform(get(BASE_URL +"/"+ userId))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        final User actualUser = objectMapper.readValue(userJson, User.class);
        assertEquals(getMockUsers().get(0), actualUser);
    }

    @Test
    public void getUserById_NotFound() throws Exception {
        String userId = "jdoe";

        when(userService.getUserById(userId)).thenReturn(null);
        mockMvc.perform(get(BASE_URL +"/"+ userId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(getMockUsers());

        final String userJson = mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        final User[] actualUsersArray = objectMapper.readValue(userJson, User[].class);
        List<User> actualUsers = Arrays.asList(actualUsersArray);

        assertEquals(getMockUsers().size(), actualUsers.size());
        assertEquals(getMockUsers().get(0), actualUsers.get(0));
        assertEquals(getMockUsers().get(1), actualUsers.get(1));

    }

    @Test
    public void createUser() throws Exception {

        String requestJson = "{\"userId\":\"jdoe\",\n" +
                "\"email\":\"jdoe@gmail.com\",\n" +
                "\"name\":\"John Doe\"\n" +
                "}";

        mockMvc.perform(post(BASE_URL)
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated());
    }

    @Test
    public void createUserRequestForExistingUser() throws Exception {

        String requestJson = "{\"userId\":\"jdoe\",\n" +
                "\"email\":\"jdoe@gmail.com\",\n" +
                "\"name\":\"John Doe\"\n" +
                "}";

        when(userService.getUserById("jdoe")).thenReturn(getMockUsers().get(0));

        mockMvc.perform(post(BASE_URL)
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void createUserRequest_InvalidEmail() throws Exception {

        String requestJson = "{\"userId\":\"jdoe\",\n" +
                "\"email\":\"jdoegmail.com\",\n" +
                "\"name\":\"John Doe\"\n" +
                "}";

        when(userService.getUserById("jdoe")).thenReturn(getMockUsers().get(0));

        mockMvc.perform(post(BASE_URL)
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteUser() throws Exception {
        when(userService.getUserById("jdoe")).thenReturn(getMockUsers().get(0));
        mockMvc.perform(delete(BASE_URL+"/"+"jdoe"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteUserRequestForNonExistentUser() throws Exception {
        when(userService.getUserById("jdoe")).thenReturn(null);

        mockMvc.perform(delete(BASE_URL+"/"+"jdoe"))
                .andExpect(status().isNotFound());
    }

    private List<User> getMockUsers() {

        User user = new User();
        user.setUserId("jdoe");
        user.setEmail("jdoe@gmail.com");
        user.setName("John Doe");

        User user1 = new User();
        user1.setUserId("janedoe");
        user1.setEmail("janedoe@gmail.com");
        user1.setName("Jane Doe");

        return Arrays.asList(user, user1);
    }

}