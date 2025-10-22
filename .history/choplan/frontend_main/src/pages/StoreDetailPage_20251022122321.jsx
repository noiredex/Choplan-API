import React from "react";
import "../styles/StoreDetailPage.css";

export default function StoreDetailPage() {
  return (
    <div className="store-detail-page">
      <header className="store-header">
        <h2>매장 상세정보 페이지</h2>
        <p>이곳에 매장 이름 / 평점 / 위치 / 영업시간 등의 정보 표시</p>
      </header>

      <section className="store-info-section">
        <div className="store-images">[매장 이미지 슬라이드 영역]</div>
        <div className="store-basic-info">
          <ul>
            <li>주소: 서울시 강남구 …</li>
            <li>주차: 가능</li>
            <li>룸 보유: 있음</li>
            <li>최대 인원: 8명</li>
            <li>콜키지: 가능</li>
          </ul>
        </div>
      </section>

      <section className="store-menu-section">
        <h3>메뉴</h3>
        <div className="menu-list">[메뉴 목록 표시 영역]</div>
      </section>

      <section className="store-review-section">
        <h3>리뷰</h3>
        <div className="review-list">[리뷰 목록 표시 영역]</div>
      </section>

      <footer className="store-footer">
        <button className="back-button">뒤로가기</button>
        <button className="reserve-button">예약하기</button>
      </footer>
    </div>
  );
}
