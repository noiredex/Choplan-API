import React from "react";
import { useNavigate } from "react-router-dom";
import "../../styles/StoreDetail.css";

export default function StoreDetail() {
  const navigate = useNavigate();

  return (
    <div className="store-detail">
      <h2 className="store-title">매장 상세정보 페이지</h2>

      <div className="store-basic-info">
        <p>이곳에 매장 이름 / 평점 / 위치 / 영업시간 등의 정보 표시</p>
        <div className="store-info-grid">
          <div className="store-image-area">[매장 이미지 슬라이드 영역]</div>
          <div className="store-text-info">
            <p>주소: 서울시 강남구 ...</p>
            <p>주차: 가능</p>
            <p>룸 보유: 있음</p>
            <p>최대 인원: 8명</p>
            <p>콜키지: 가능</p>
          </div>
        </div>
      </div>

      <div className="store-section">
        <h3>메뉴</h3>
        <div className="store-menu">[메뉴 목록 표시 영역]</div>
      </div>

      <div className="store-section">
        <h3>리뷰</h3>
        <div className="store-reviews">[리뷰 목록 표시 영역]</div>
      </div>

      <div className="store-buttons">
        <button className="back-btn" onClick={() => navigate("/main")}>
          뒤로가기
        </button>
        <button className="reserve-btn" onClick={() => navigate("/reservation")}>
          예약하기
        </button>
      </div>
    </div>
  );
}
