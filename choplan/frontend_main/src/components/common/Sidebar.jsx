import React, { useState, useEffect } from "react";
import "../../styles/Sidebar.css";

const categories = [
  "전체", // 모든 매장 보기
  "다이닝",
  "뷔페",
  "미쉐린",
  "이자카야",
  "초밥",
  "한식",
  "양식",
  "중식",
  "동남아",
  "인도",
  "기타",
];

export default function Sidebar({ selectedCategory, onSelectCategory, searchQuery }) {
  const [isOpen, setIsOpen] = useState(false);

  // 검색어 → 카테고리 자동 반영
  useEffect(() => {
    if (searchQuery && categories.includes(searchQuery)) {
      onSelectCategory(searchQuery);
    } else if (!searchQuery && selectedCategory !== "전체") {
      onSelectCategory("전체");
    }
  }, [searchQuery]);

  return (
    <div className={`sidebar ${isOpen ? "open" : ""}`}>
      <button className="sidebar-toggle" onClick={() => setIsOpen(!isOpen)}>
        ☰
      </button>

      {isOpen && (
        <ul className="sidebar-list">
          {categories.map((category) => (
            <li
              key={category}
              className={selectedCategory === category ? "active" : ""}
              onClick={() => onSelectCategory(category)}
            >
              {category}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}
