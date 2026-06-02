package com.gatewayflow.service;

import com.gatewayflow.dto.request.CreateFeedbackRequest;
import com.gatewayflow.dto.response.FeedbackResponse;

import java.util.List;

public interface FeedbackService {

    FeedbackResponse create(CreateFeedbackRequest request);

    List<FeedbackResponse> findAll();
}