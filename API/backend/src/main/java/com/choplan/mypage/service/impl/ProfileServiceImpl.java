package com.choplan.mypage.service.impl;

import org.springframework.stereotype.Service;

import com.choplan.mypage.dto.ProfileDto;
import com.choplan.mypage.service.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService {
    private ProfileDto memory = new ProfileDto("sonsing", "", "", "");

    @Override
    public ProfileDto getMe() {
        return memory;
    }

    @Override
    public ProfileDto updateMe(ProfileDto payload) {
        this.memory = payload; // 실제 DB 저장으로 교체
        return memory;
    }
}
