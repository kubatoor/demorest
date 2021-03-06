package com.demo.rest.controller;

import com.demo.rest.model.Post;
import com.demo.rest.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostsController {

    private final PostService postService;

    @Autowired
    public PostsController(final PostService postService) {
        this.postService = postService;
    }

    /**
     * Controller method that return a response entity object containing a list of Posts from
     * an extnernal site.
     *
     * @return ResponseEntity ojbect containing a list of Posts from
     * an extnernal site.
     */
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPosts(){
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
    }
}
