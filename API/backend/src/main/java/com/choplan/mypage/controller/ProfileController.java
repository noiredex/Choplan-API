package com.choplan.mypage.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.choplan.mypage.dto.ProfileDto;
import com.choplan.mypage.service.ProfileService;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    private final ProfileService profileService;
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/me")
    public ProfileDto getMe() {
        return profileService.getMe();
    }

    @PutMapping("/me")
    public ProfileDto update(@RequestBody ProfileDto payload) {
        return profileService.updateMe(payload);
    }
}
