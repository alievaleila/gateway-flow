package com.gatewayflow.dto.response;

public record ProfileResponse(

        Long id,
        String name,
        String email,
        String bio
) {}