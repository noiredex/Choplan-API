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

  // 검색어가 입력되면 카테고리 자동 반영
  useEffect(() => {
    if (searchQuery && searchQuery.trim() !== "") {
      const matched = categories.find((cat) => searchQuery.includes(cat));
      if (matched) onSelectCategory(matched);
    }
  }, [searchQuery]);

  // 외부 클릭 시 사이드바 닫힘 (모바일에서만)
  useEffect(() => {
    const handleClickOutside = (e) => {
      const sidebar = document.querySelector(".sidebar");
      const toggle = document.querySelector(".sidebar-toggle");
      if (isOpen && sidebar && !sidebar.contains(e.target) && !toggle.contains(e.target)) {
        setIsOpen(false);
      }
    };
    document.addEventListener("click", handleClickOutside);
    return () => document.removeEventListener("click", handleClickOutside);
  }, [isOpen]);

  return (
    <>
      {/* 햄버거 버튼 */}
      <button
        className="sidebar-toggle"
        onClick={() => setIsOpen(!isOpen)}
        aria-label="메뉴 열기"
      >
        ☰
      </button>

      {/* 오버레이 (열렸을 때만 표시) */}
      {isOpen && <div className="overlay" onClick={() => setIsOpen(false)}></div>}

      {/* 사이드바 영역 */}
      <aside className={`sidebar ${isOpen ? "open" : ""}`}>
        <ul className="sidebar-list">
          {categories.map((category, index) => (
            <li
              key={index}
              className={selectedCategory === category ? "active" : ""}
              onClick={() => {
                onSelectCategory(category);
                setIsOpen(false); // 클릭 시 닫기 (모바일 UX)
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
