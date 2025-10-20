import React from "react";
import "../../styles/Navbar.css";

export default function Navbar() {
  return (
    <nav className="navbar">
      <button className="menu-btn">☰</button>
      <input
        type="text"
        placeholder="가게명, 지역, 업종으로 검색"
        className="search-bar"
      />
      <button className="profile-btn">👤</button>
    </nav>
  );
}
