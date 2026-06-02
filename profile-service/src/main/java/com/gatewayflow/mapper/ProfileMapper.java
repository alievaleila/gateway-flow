package com.gatewayflow.mapper;

import com.gatewayflow.dto.request.CreateProfileRequest;
import com.gatewayflow.dto.request.ProfileUpdateRequest;
import com.gatewayflow.dto.response.ProfileResponse;
import com.gatewayflow.entity.Profile;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    Profile toEntity(CreateProfileRequest request);

    ProfileResponse toResponse(Profile profile);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProfile(ProfileUpdateRequest request, @MappingTarget Profile profile);
}