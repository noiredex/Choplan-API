package com.choplan.mypage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.choplan.mypage.dto.ReviewDto;
import com.choplan.mypage.dto.ScrapDto;
import com.choplan.mypage.service.MyPageService;

@RestController
@RequestMapping("/api/mypage")
public class MyPageController {
    private final MyPageService myPageService;

    public MyPageController(MyPageService myPageService) {
        this.myPageService = myPageService;
    }

    @GetMapping("/summary")
    public Map<String, Object> summary() {
        return myPageService.summary();
    }

    @GetMapping("/reviews")
    public List<ReviewDto> reviews() {
        return myPageService.myReviews();
    }

    @GetMapping("/scraps")
    public List<ScrapDto> scraps() {
        return myPageService.myScraps();
    }
}
