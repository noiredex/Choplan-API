import React from "react";
import "../../styles/BannerSlider.css"; // 스타일 폴더 경로 확인 — home 기준 두 단계 위로

export default function BannerSlider() {
  return (
    <div className="banner-slider">
      <div className="banner-content">
        <h2 className="banner-title">오늘의 추천 매장</h2>
        <div className="banner-slides">
          {/* TODO: 슬라이더 라이브러리 연결 전 임시 영역 */}
          <div className="slide-item">이미지 1</div>
          <div className="slide-item">이미지 2</div>
          <div className="slide-item">이미지 3</div>
        </div>
      </div>
    </div>
  );
}
