package com.gatewayflow.dto.response;

import java.time.LocalDateTime;

public record FeedbackResponse(

        Long id,
        String username,
        String message,
        LocalDateTime createdAt
) {}