import React, { useState, useEffect } from "react";
import "../../styles/Sidebar.css";

export default function Sidebar({ selectedCategory, onSelectCategory, searchQuery }) {
  const [isOpen, setIsOpen] = useState(false);

  const categories = [
    "전체",
    "이자카야",
    "초밥",
    "한식",
    "양식",
    "중식",
    "동남아",
    "인도",
    "기타",
  ];

  // 검색어에 따라 자동 카테고리 선택
  useEffect(() => {
    if (searchQuery && searchQuery.trim() !== "") {
      const matched = categories.find((cat) => searchQuery.includes(cat));
      if (matched) onSelectCategory(matched);
    }
  }, [searchQuery]);

  return (
    <>
      {/* 햄버거 버튼 (항상 노출) */}
      <button
        className="sidebar-toggle"
        onClick={() => setIsOpen(!isOpen)}
        aria-label="메뉴 열기"
      >
        ☰
      </button>

      {/* 오버레이 (열릴 때만) */}
      {isOpen && <div className="overlay" onClick={() => setIsOpen(false)}></div>}

      {/* 세로형 사이드바 */}
      <aside className={`sidebar ${isOpen ? "open" : ""}`}>
        <ul className="sidebar-list">
          {categories.map((category, index) => (
            <li
              key={index}
              className={selectedCategory === category ? "active" : ""}
              onClick={() => {
                onSelectCategory(category);
                setIsOpen(false);
              }}
            >
              {category}
            </li>
          ))}
        </ul>
      </aside>
    </>
  );
}
