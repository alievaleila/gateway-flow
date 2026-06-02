package com.gatewayflow.mapper;

import com.gatewayflow.dto.request.CreateFeedbackRequest;
import com.gatewayflow.dto.response.FeedbackResponse;
import com.gatewayflow.entity.Feedback;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FeedbackMapper {

    Feedback toEntity(CreateFeedbackRequest request);

    FeedbackResponse toResponse(Feedback feedback);
}