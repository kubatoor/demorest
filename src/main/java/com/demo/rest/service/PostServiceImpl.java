package com.demo.rest.service;

import com.demo.rest.client.PostFeignClient;
import com.demo.rest.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostFeignClient postFeignClient;

    @Autowired
    public PostServiceImpl(final PostFeignClient postFeignClient) {
        this.postFeignClient = postFeignClient;
    }

    @Override
    public List<Post> getAllPosts() {
        return postFeignClient.getAllPosts();
    }
}
