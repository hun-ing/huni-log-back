package com.huni.blog.controller;

import com.huni.blog.request.post.PostRequest;
import com.huni.blog.response.ApiResponse;
import com.huni.blog.response.PostResponse;
import com.huni.blog.service.PostService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

  private final PostService postService;

  @GetMapping
  public ApiResponse<List<PostResponse>> getAllPosts() {
    List<PostResponse> posts = postService.getAllPosts();
    return ApiResponse.ok(posts);
  }

  @GetMapping("/{id}")
  public ApiResponse<PostResponse> getPost(@PathVariable Long id) {
    PostResponse postResponse = postService.getPostById(id);
    return ApiResponse.ok(postResponse);
  }

  @PostMapping
  public ApiResponse<PostResponse> createPost(@Valid @RequestBody PostRequest requestBody) {
    PostResponse postResponse = postService.createPost(requestBody);
    return ApiResponse.of(HttpStatus.CREATED, postResponse);
  }

  @PutMapping("/{id}")
  public ApiResponse<PostResponse> updatePost(@PathVariable Long id,
      @Valid @RequestBody PostRequest requestBody) {
    PostResponse updatedPost = postService.updatePost(id, requestBody);
    return ApiResponse.ok(updatedPost);
  }

  @DeleteMapping("/{id}")
  public ApiResponse<Void> deletePost(@PathVariable Long id) {
    postService.deletePost(id);
    return ApiResponse.ok();
  }

}
