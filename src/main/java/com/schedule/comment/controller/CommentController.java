package com.schedule.comment.controller;

import com.schedule.auth.dto.SessionAuth;
import com.schedule.comment.dto.CreateCommentRequest;
import com.schedule.comment.dto.CreateCommentResponse;
import com.schedule.comment.dto.GetCommentResponse;
import com.schedule.comment.service.CommentService;
import com.schedule.schedule.dto.GetScheduleResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<CreateCommentResponse> createComment(@PathVariable Long scheduleId, @Valid @RequestBody CreateCommentRequest request, HttpSession session) {
        SessionAuth loginAuth = (SessionAuth) session.getAttribute("loginAuth");
        if (loginAuth == null){
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }
        CreateCommentResponse response = commentService.createComment(scheduleId, loginAuth, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/schedules/{scheduleId}/comments")
    public  ResponseEntity<List<GetCommentResponse>> getComment(@PathVariable Long scheduleId) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getAll(scheduleId));
    }

}
