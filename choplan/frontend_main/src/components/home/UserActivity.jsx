import React from "react";
import "../../styles/UserActivity.css";

export default function UserActivity() {
  return (
    <section className="user-activity">
      <h2>나의 최근 활동</h2>
      <ul>
        <li>✔️ 지난주 방문: 오복 이자카야</li>
        <li>⭐ 작성한 리뷰: 한남 초밥집</li>
        <li>📅 예약 내역: 10월 22일 (토) 오후 6시</li>
      </ul>
    </section>
  );
}
