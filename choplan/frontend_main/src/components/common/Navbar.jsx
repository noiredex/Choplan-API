import React from "react";
import Sidebar from "./Sidebar";
import "../../styles/Navbar.css";

export default function Navbar({ setSelectedCategory }) {
  return (
    <nav className="navbar">
      <Sidebar setSelectedCategory={setSelectedCategory} />
      <div className="navbar-title">Choplan</div>
      <input type="text" placeholder="식당, 지역, 키워드 검색" className="search-input" />
      <div className="profile-icon">👤</div>
    </nav>
  );
}
