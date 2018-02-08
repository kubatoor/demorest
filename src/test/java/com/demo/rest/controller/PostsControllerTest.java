package com.demo.rest.controller;

import com.demo.rest.model.Post;
import com.demo.rest.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.boot.jaxb.SourceType;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PostsControllerTest {
    private MockMvc mockMvc;
    @Mock
    private PostService postService;
    @InjectMocks
    private PostsController postsController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(postsController).build();
    }

    @Test
    public void getAllPosts() throws Exception {
        when(postService.getAllPosts()).thenReturn(mockPosts());
        final String actualContent = mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn().getResponse().getContentAsString();
        assertEquals(expectedJson(),actualContent);
    }

    private List<Post> mockPosts() {
        Post post1 = new Post();
        post1.setBody("est rerum tempore vitae sequi sint nihil reprehenderit dolor beatae" +
                " ea dolores neque fugiat blanditiis voluptate" +
                " porro vel nihil molestiae ut reiciendis qui aperiam " +
                "non debitis possimus qui neque nisi nulla");
        post1.setId(1);
        post1.setTitle("qui est esse");
        post1.setUserId(1);

        Post post2 = new Post();
        post2.setBody("et iusto sed quo iure\\nvoluptatem occaecati omnis " +
                "eligendi aut ad\\nvoluptatem doloribus vel accusantium quis" +
                " pariatur\\nmolestiae porro eius odio et labore et velit aut");
        post2.setId(3);
        post2.setTitle("ea molestias quasi exercitationem repellat qui ipsa sit aut");
        post2.setUserId(1);
        return Arrays.asList(post1, post2);
    }

    private String expectedJson(){
        return "[{\"userId\":1,\"id\":1,\"title\":\"qui est esse\"," +
                "\"body\":\"est rerum tempore vitae sequi sint nihil " +
                "reprehenderit dolor beatae ea dolores neque fugiat " +
                "blanditiis voluptate porro vel nihil molestiae ut " +
                "reiciendis qui aperiam non debitis possimus qui neque nisi nulla\"}," +
                "{\"userId\":1,\"id\":3,\"title\":\"ea molestias quasi exercitationem repellat qui ipsa sit aut\"," +
                "\"body\":\"et iusto sed quo iure\\\\nvoluptatem occaecati omnis eligendi aut ad\\\\nvoluptatem" +
                " doloribus vel accusantium quis pariatur\\\\nmolestiae porro eius odio et labore et velit aut\"}]";
    }

}