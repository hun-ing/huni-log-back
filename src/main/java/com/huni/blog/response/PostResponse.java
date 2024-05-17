package com.huni.blog.response;

import java.time.LocalDateTime;
import java.util.List;

public record PostResponse(
    Long id,
    String title,
    String content,
    Long authorId,
    List<Long> categoryIds,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}
