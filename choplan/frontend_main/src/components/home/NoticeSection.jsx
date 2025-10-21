import React from "react";
import "../../styles/NoticeSection.css";

export default function NoticeSection() {
  return (
    <section className="notice-section">
      <h2>공지사항</h2>
      <ul>
        <li>📢 신규 가입 고객 대상 10% 할인 이벤트!</li>
        <li>🚨 예약 노쇼(No-show) 방지 캠페인 안내</li>
        <li>🌟 추천 맛집 TOP 10 업데이트 완료!</li>
      </ul>
    </section>
  );
}
