package com.gatewayflow.service;

import com.gatewayflow.dto.request.CreateProfileRequest;
import com.gatewayflow.dto.request.ProfileUpdateRequest;
import com.gatewayflow.dto.response.ProfileResponse;

import java.util.List;

public interface ProfileService {

    ProfileResponse create(CreateProfileRequest request);

    List<ProfileResponse> findAll();

    ProfileResponse findById(Long id);

    void deleteById(Long id);

    ProfileResponse update(Long id, ProfileUpdateRequest request);
}
