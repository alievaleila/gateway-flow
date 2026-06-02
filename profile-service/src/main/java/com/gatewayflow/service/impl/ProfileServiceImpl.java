package com.gatewayflow.service.impl;

import com.gatewayflow.dto.request.CreateProfileRequest;
import com.gatewayflow.dto.request.ProfileUpdateRequest;
import com.gatewayflow.dto.response.ProfileResponse;
import com.gatewayflow.entity.Profile;
import com.gatewayflow.exception.DuplicateEmailException;
import com.gatewayflow.exception.ResourceNotFoundException;
import com.gatewayflow.mapper.ProfileMapper;
import com.gatewayflow.repository.ProfileRepository;
import com.gatewayflow.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    @Override
    public ProfileResponse create(CreateProfileRequest request) {

        log.info("Creating profile with email: {}", request.email());

        if (profileRepository.findByEmail(request.email()).isPresent()) {

            log.warn("Profile creation failed. Email already exists: {}",
                    request.email());

            throw new DuplicateEmailException(
                    "Email already exists"
            );
        }

        Profile profile = profileMapper.toEntity(request);

        Profile savedProfile = profileRepository.save(profile);

        log.info("Profile created successfully with id: {}",
                savedProfile.getId());

        return profileMapper.toResponse(savedProfile);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProfileResponse> findAll() {

        log.info("Fetching all profiles");

        List<Profile> profiles = profileRepository.findAll();

        log.info("Total profiles fetched: {}", profiles.size());

        return profiles.stream()
                .map(profileMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProfileResponse findById(Long id) {

        log.info("Fetching profile with id: {}", id);

        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> {

                    log.error("Profile not found with id: {}", id);

                    return new ResourceNotFoundException(
                            "Profile not found with id: " + id
                    );
                });

        log.info("Profile fetched successfully with id: {}", id);

        return profileMapper.toResponse(profile);
    }

    @Override
    public ProfileResponse update(
            Long id,
            ProfileUpdateRequest request
    ) {

        log.info("Updating profile with id: {}", id);

        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> {

                    log.error("Profile not found for update with id: {}", id);

                    return new ResourceNotFoundException(
                            "Profile not found with id: " + id
                    );
                });

        Profile existingProfile =
                profileRepository.findByEmail(request.email())
                        .orElse(null);

        if (existingProfile != null &&
                !existingProfile.getId().equals(id)) {

            log.warn("Email already exists during update: {}",
                    request.email());

            throw new DuplicateEmailException(
                    "Email already exists"
            );
        }

        profileMapper.updateProfile(request, profile);

        Profile updatedProfile = profileRepository.save(profile);

        log.info("Profile updated successfully with id: {}", id);

        return profileMapper.toResponse(updatedProfile);
    }

    @Override
    public void deleteById(Long id) {

        log.info("Deleting profile with id: {}", id);

        profileRepository.deleteById(id);

        if (!profileRepository.existsById(id)) {
            throw new ResourceNotFoundException("Profile not found with id: " + id);
        }
        profileRepository.deleteById(id);

        log.info("Profile deleted successfully with id: {}", id);
    }
}