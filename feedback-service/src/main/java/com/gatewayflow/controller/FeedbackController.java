package com.gatewayflow.controller;

import com.gatewayflow.dto.request.CreateFeedbackRequest;
import com.gatewayflow.dto.response.FeedbackResponse;
import com.gatewayflow.service.FeedbackService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feedback")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FeedbackResponse create(
            @Valid @RequestBody CreateFeedbackRequest request
    ) {

        return feedbackService.create(request);
    }

    @GetMapping
    public List<FeedbackResponse> findAll() {

        return feedbackService.findAll();
    }
}