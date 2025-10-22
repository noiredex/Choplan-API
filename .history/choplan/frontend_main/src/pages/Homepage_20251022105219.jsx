import React, { useState } from "react";
import Sidebar from "../components/common/Sidebar";
import SearchBar from "../components/home/SearchBar";
import BannerSlider from "../components/home/BannerSlider";
import RecommendedStores from "../components/home/RecommendedStores";
import "../styles/HomePage.css";

export default function HomePage() {
  const [selectedCategory, setSelectedCategory] = useState("전체"); // 기본값 유지
  const [searchQuery, setSearchQuery] = useState("");

  return (
    <div className="homepage-container">
      {/* Sidebar - 양방향 동기화 유지 */}
      <Sidebar
        selectedCategory={selectedCategory}
        onSelectCategory={setSelectedCategory}
        searchQuery={searchQuery}
      />

      <div className="main-content">
        {/* SearchBar */}
        <SearchBar onSearch={setSearchQuery} />

        {/* 배너 (자사 이벤트 or 공지 배너용) */}
        <BannerSlider />

        {/* 추천 매장 리스트 (검색 & 카테고리 동시 반영) */}
        <RecommendedStores
          selectedCategory={selectedCategory}
          searchQuery={searchQuery}
        />
      </div>
    </div>
  );
}
