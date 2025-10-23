import React, { useState, useEffect } from "react";
import "../../styles/Sidebar.css";

export default function Sidebar({
  selectedCategory,
  onSelectCategory,
  searchQuery,
}) {
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

  // 검색어가 변경되면 해당 카테고리 자동 선택
  useEffect(() => {
    if (searchQuery && searchQuery.trim() !== "") {
      const matchedCategory = categories.find((cat) =>
        searchQuery.includes(cat)
      );
      if (matchedCategory) {
        onSelectCategory(matchedCategory);
      }
    }
  }, [searchQuery]);

  return (
    <>
      {/* 햄버거 버튼 */}
      <button
        className="sidebar-toggle"
        onClick={() => setIsOpen(!isOpen)}
        aria-label="Toggle sidebar"
      >
        ☰
      </button>

      {/* 사이드바 */}
      <div className={`sidebar ${isOpen ? "open" : "closed"}`}>
        <ul className="sidebar-list">
          {categories.map((category, index) => (
            <li
              key={index}
              className={selectedCategory === category ? "active" : ""}
              onClick={() => {
                onSelectCategory(category);
                setIsOpen(false); // 모바일에서 클릭 시 자동 닫힘
              }}
            >
              {category}
            </li>
          ))}
        </ul>
      </div>
    </>
  );
}
