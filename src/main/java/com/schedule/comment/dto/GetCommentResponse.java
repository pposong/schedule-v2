package com.schedule.comment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class GetCommentResponse {

    private final Long id;
    private final String authorName;
    private final String content;
    private final LocalDateTime createAt;
    private final LocalDateTime modifiedAt;
}
