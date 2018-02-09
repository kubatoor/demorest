package com.demo.rest.service;

import com.demo.rest.entity.User;
import com.demo.rest.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void getUserById() throws Exception {
        when(userRepository.findByUserId("jdoe")).thenReturn(getMockUsers().get(0));
        final User userById = userService.getUserById("jdoe");
        assertEquals(getMockUsers().get(0),userById);
    }

    @Test
    public void saveUser() throws Exception {
        when(userRepository.save(getMockUsers().get(0))).thenReturn(getMockUsers().get(0));
        userService.saveUser(getMockUsers().get(0));
        verify(userRepository,times(1)).save(getMockUsers().get(0));
    }

    @Test
    public void deleteUserById() throws Exception {
        userService.deleteUserById("jdoe");
        verify(userRepository,times(1)).delete("jdoe");
    }

    @Test
    public void getAllUsers() throws Exception {
        when(userRepository.findAll()).thenReturn(getMockUsers());
        final List<User> allUsers = userService.getAllUsers();
        verify(userRepository, times(1)).findAll();
        assertEquals(getMockUsers().size(), allUsers.size());
        assertEquals(getMockUsers().get(0), allUsers.get(0));
        assertEquals(getMockUsers().get(1), allUsers.get(1));
    }

    @Test
    public void getAllUsers_EmptyList() throws Exception {
        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        final List<User> allUsers = userService.getAllUsers();
        verify(userRepository, times(1)).findAll();
        assertTrue(allUsers.size()==0);
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