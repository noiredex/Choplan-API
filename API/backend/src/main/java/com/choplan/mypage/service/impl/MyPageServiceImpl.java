package com.choplan.mypage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.choplan.mypage.dto.ReviewDto;
import com.choplan.mypage.dto.ScrapDto;
import com.choplan.mypage.service.MyPageService;

@Service
public class MyPageServiceImpl implements MyPageService {
    @Override
    public Map<String, Object> summary() {
        return Map.of(
                "nickname", "게스트",
                "recentVisited", 0);
    }

    @Override
    public List<ReviewDto> myReviews() {
        return List.of(
                new ReviewDto(1L, "매장A", "테스트리뷰작성글"),
                new ReviewDto(2L, "매장B", "리뷰입니다"),
                new ReviewDto(3L, "찹플랜", "더미데이터입니다"));
    }

    @Override
    public List<ScrapDto> myScraps() {
        return List.of(
                new ScrapDto(1L, "매장C", "2025-10-15"),
                new ScrapDto(2L, "찹플랜", "2025-10-20"));
    }
}
