package com.demo.rest.service;

import com.demo.rest.client.PostFeignClient;
import com.demo.rest.model.Post;
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

public class PostServiceImplTest {

    @Mock
    private PostFeignClient postFeignClient;
    @InjectMocks
    private PostServiceImpl postService;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllPosts() throws Exception {
        when(postFeignClient.getAllPosts()).thenReturn(mockPosts());
        final List<Post> allPosts = postService.getAllPosts();
        assertEquals(allPosts, mockPosts());
        verify(postFeignClient, times(1)).getAllPosts();

    }

    @Test
    public void getEmptyPosts() throws Exception {
        when(postFeignClient.getAllPosts()).thenReturn(new ArrayList<>());
        final List<Post> allPosts = postService.getAllPosts();
        assertTrue(allPosts.size()==0);
        verify(postFeignClient, times(1)).getAllPosts();

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
        post2.setBody("est rerum tempore vitae sequi sint nihil reprehenderit dolor beatae" +
                " ea dolores neque fugiat blanditiis voluptate" +
                " porro vel nihil molestiae ut reiciendis qui aperiam " +
                "non debitis possimus qui neque nisi nulla");
        post2.setId(3);
        post2.setTitle("qui est esse");
        post2.setUserId(2);
        return Arrays.asList(post1, post2);
    }

}