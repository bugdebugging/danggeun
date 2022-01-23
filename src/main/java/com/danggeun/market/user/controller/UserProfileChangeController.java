package com.danggeun.market.user.controller;

import com.danggeun.market.common.api.ApiResult;
import com.danggeun.market.common.api.ApiUtils;
import com.danggeun.market.common.file.FileUploader;
import com.danggeun.market.user.domain.UserImage;
import com.danggeun.market.user.dto.UserProfileChangeRequest;
import com.danggeun.market.user.dto.UserSummaryResponse;
import com.danggeun.market.user.service.UserProfileChangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserProfileChangeController {
    private final UserProfileChangeService userProfileChangeService;
    private final FileUploader fileUploader;

    @PutMapping(value = "/{userId}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ApiResult<UserSummaryResponse> changeProfile(@PathVariable("userId") Long userId,
                                                        @RequestPart(value = "profile", required = false) UserProfileChangeRequest profileChangeRequest,
                                                        @RequestPart(value = "profile_image", required = false) MultipartFile file) {
        String profileImagePath = null;
        try {
            profileImagePath = fileUploader.upload("users/" + userId, file);
        } catch (IOException exception) {
            throw new IllegalArgumentException("이미지 업로드 실패:" + exception.getMessage());
        }

        return ApiUtils.success(userProfileChangeService.changeProfile(userId, UserImage.of(profileImagePath), profileChangeRequest.getNickname()));
    }
}
