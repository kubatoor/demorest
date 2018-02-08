package com.demo.rest.client;


import com.demo.rest.model.Post;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "posts", url = "${post.endpoint.url}")
public interface PostFeignClient {

    @GetMapping
    List<Post> getAllPosts();
}
