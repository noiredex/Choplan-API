package com.choplan.mypage.service;

import java.util.List;
import java.util.Map;

import com.choplan.mypage.dto.ReviewDto;
import com.choplan.mypage.dto.ScrapDto;

public interface MyPageService {
    Map<String, Object> summary();
    List<ReviewDto> myReviews();
    List<ScrapDto> myScraps();
}
