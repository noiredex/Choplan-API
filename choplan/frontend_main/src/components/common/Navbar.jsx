import React from "react";
import Sidebar from "./Sidebar";
import "../../styles/Navbar.css";

export default function Navbar() {
  return (
    <nav className="navbar">
      <Sidebar />
      <div className="navbar-title">Choplan</div>
      <input type="text" placeholder="식당, 지역, 키워드 검색" className="search-input" />
      <div className="profile-icon">👤</div>
    </nav>
  );
}
