import React from "react";
import "../../styles/Navbar.css";

export default function Footer() {
  const tabs = ["홈", "검색", "리뷰작성", "예약확인", "프로필"];

  return (
    <footer className="footer-nav">
      {tabs.map((tab, index) => (
        <button key={index} className="footer-btn">
          {tab}
        </button>
      ))}
    </footer>
  );
}
