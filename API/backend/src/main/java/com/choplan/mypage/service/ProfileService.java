package com.choplan.mypage.service;

import com.choplan.mypage.dto.ProfileDto;

public interface ProfileService {
    ProfileDto getMe();
    ProfileDto updateMe(ProfileDto payload);    
}
