import React, { useState } from "react";
import "../../styles/Sidebar.css";

export default function Sidebar() {
    const [isOpen, setIsOpen] = useState(flase);

    const toggleSidebar = () => setIsOpen(!isOpen);

    return (
        <>
        <button className="menu-btn" onClick={toggleSidebar}>
        ☰
        </button>

<div className={`sidebar ${isOpen ? "open" : ""}`}>
    <div className="sidebar-header">
        <h3>업종 카테고리</h3>
        <button onClick={togglesSidebar}>x</button>
    </div>

    <ul className="sidebar-menu">
        {["다이닝", "이자카야", "초밥", "한식", "양식", "중식", "동남아", "인도", "기타"].map((category) => (
            <li key={category}>{category}</li>
        ))}
        </ul>
        <hr />
        <ul className="sidebar-menu">
        {["내 프로필", "예약 내역", "리뷰관리", "공지사항", "로그아웃"].map((item) => (
            <li key={item}>{item}</li>
        ))}
    </ul>
</div>
</>
    )
}