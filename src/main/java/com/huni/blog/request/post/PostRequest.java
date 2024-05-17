package com.huni.blog.request.post;


import java.util.List;

public record PostRequest(
    String title,
    String content,
    List<Long> categoryIds
) {

}
