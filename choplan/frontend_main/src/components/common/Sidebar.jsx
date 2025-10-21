import React, { useState } from "react";
import "../../styles/Sidebar.css";

export default function Sidebar() {
  const [isOpen, setIsOpen] = useState(false);

  const toggleSidebar = () => setIsOpen(!isOpen);

  return (
    <>
      <button className="menu-btn" onClick={toggleSidebar}>
        ☰
      </button>

      <div className={`sidebar ${isOpen ? "open" : ""}`}>
        <div className="sidebar-header">
          <h3>업종 카테고리</h3>
          <button onClick={toggleSidebar}>✕</button>
        </div>

        <ul className="sidebar-menu">
          {["다이닝", "이자카야", "초밥", "한식", "양식", "중식", "동남아", "인도", "기타"].map(
            (category) => (
              <li key={category}>{category}</li>
            )
          )}
        </ul>

        <hr />

        <ul className="sidebar-footer">
          <li>내 프로필</li>
          <li>예약 내역</li>
          <li>리뷰 관리</li>
          <li>공지사항</li>
        </ul>
      </div>

      {isOpen && <div className="sidebar-overlay" onClick={toggleSidebar}></div>}
    </>
  );
}
