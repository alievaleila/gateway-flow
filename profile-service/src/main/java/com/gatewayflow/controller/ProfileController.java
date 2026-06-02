package com.gatewayflow.controller;

import com.gatewayflow.dto.request.CreateProfileRequest;
import com.gatewayflow.dto.request.ProfileUpdateRequest;
import com.gatewayflow.dto.response.ProfileResponse;
import com.gatewayflow.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProfileResponse create(
            @Valid @RequestBody CreateProfileRequest request
    ) {return profileService.create(request);}

    @GetMapping
    public List<ProfileResponse> findAll() {

        return profileService.findAll();
    }

    @GetMapping("/{id}")
    public ProfileResponse findById(
            @PathVariable Long id
    ) {

        return profileService.findById(id);
    }

    @PutMapping("/{id}")
    public ProfileResponse updateProfile(
            @PathVariable Long id,
            @Valid @RequestBody ProfileUpdateRequest request
    ) {

        return profileService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(
            @PathVariable Long id
    ) {

        profileService.deleteById(id);
    }
}