package com.gatewayflow.service.impl;

import com.gatewayflow.dto.request.CreateFeedbackRequest;
import com.gatewayflow.dto.response.FeedbackResponse;
import com.gatewayflow.entity.Feedback;
import com.gatewayflow.mapper.FeedbackMapper;
import com.gatewayflow.repository.FeedbackRepository;
import com.gatewayflow.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final FeedbackMapper feedbackMapper;

    @Override
    public FeedbackResponse create(CreateFeedbackRequest request) {

        log.info("Creating feedback from user: {}", request.username());

        Feedback feedback = feedbackMapper.toEntity(request);

        Feedback savedFeedback = feedbackRepository.save(feedback);

        log.info("Feedback created successfully with id: {}",
                savedFeedback.getId());

        return feedbackMapper.toResponse(savedFeedback);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FeedbackResponse> findAll() {

        log.info("Fetching all feedback entries");

        List<Feedback> feedbacks = feedbackRepository.findAll();

        log.info("Total feedback entries fetched: {}",
                feedbacks.size());

        return feedbacks.stream()
                .map(feedbackMapper::toResponse)
                .toList();
    }
}