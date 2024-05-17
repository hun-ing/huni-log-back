package com.huni.blog.service;

import com.huni.blog.request.post.PostRequest;
import com.huni.blog.response.PostResponse;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PostService {

  public List<PostResponse> getAllPosts() {
    return null;
  }

  public PostResponse getPostById(Long id) {
    return null;
  }

  public PostResponse createPost(PostRequest postCreate) {
    return null;
  }

  public PostResponse updatePost(Long id, PostRequest postCreate) {
    return null;
  }

  public void deletePost(Long id) {

  }
}
