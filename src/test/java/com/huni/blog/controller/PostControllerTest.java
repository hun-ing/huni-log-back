package com.huni.blog.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.huni.blog.request.post.PostRequest;
import com.huni.blog.response.ApiResponse;
import com.huni.blog.response.PostResponse;
import com.huni.blog.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PostControllerTest {

  @InjectMocks
  private PostController postController;

  @Mock
  private PostService postService;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void getAllPostsReturnsExpectedPosts() {
    PostResponse postResponse1 = new PostResponse(
        1L,
        "title",
        "content",
        1L,
        List.of(1L),
        null,
        null
    );
    PostResponse postResponse2 = new PostResponse(
        2L,
        "title",
        "content",
        1L,
        List.of(1L),
        null,
        null
    );
    List<PostResponse> expectedPosts = Arrays.asList(postResponse1, postResponse2);

    when(postService.getAllPosts()).thenReturn(expectedPosts);

    ApiResponse<List<PostResponse>> response = postController.getAllPosts();

    assertEquals(HttpStatus.OK, response.getStatus());
    assertEquals(expectedPosts, response.getData());
  }

  @Test
  public void getPostReturnsExpectedPost() {
    Long id = 1L;
    PostResponse expectedPost = new PostResponse(
        id,
        "title",
        "content",
        1L,
        List.of(1L),
        null,
        null
    );

    when(postService.getPostById(id)).thenReturn(expectedPost);

    ApiResponse<PostResponse> response = postController.getPost(id);

    assertEquals(HttpStatus.OK, response.getStatus());
    assertEquals(expectedPost, response.getData());
  }

  @Test
  public void createPostReturnsCreatedPost() {
    PostRequest postRequest = new PostRequest(
        "title",
        "content",
        List.of(1L)
    );
    PostResponse expectedPost = new PostResponse(
        1L,
        "title",
        "content",
        1L,
        List.of(1L),
        null,
        null
    );

    when(postService.createPost(postRequest)).thenReturn(expectedPost);

    ApiResponse<PostResponse> response = postController.createPost(postRequest);

    assertEquals(HttpStatus.CREATED, response.getStatus());
    assertEquals(expectedPost, response.getData());
  }

  @Test
  public void updatePostReturnsUpdatedPost() {
    Long id = 1L;
    PostRequest postRequest = new PostRequest(
        "title",
        "content",
        List.of(1L)
    );
    PostResponse expectedPost = new PostResponse(
        id,
        "title",
        "content",
        1L,
        List.of(1L),
        null,
        null
    );

    when(postService.updatePost(id, postRequest)).thenReturn(expectedPost);

    ApiResponse<PostResponse> response = postController.updatePost(id, postRequest);

    assertEquals(HttpStatus.OK, response.getStatus());
    assertEquals(expectedPost, response.getData());
  }

  @Test
  public void deletePostReturnsOkStatus() {
    Long id = 1L;

    doNothing().when(postService).deletePost(id);

    ApiResponse<Void> response = postController.deletePost(id);

    assertEquals(HttpStatus.OK, response.getStatus());
  }
}