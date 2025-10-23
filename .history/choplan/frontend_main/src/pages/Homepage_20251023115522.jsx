import React, { useState } from "react";
import Sidebar from "../components/common/Sidebar";
import SearchBar from "../components/home/SearchBar";
import BannerSlider from "../components/home/BannerSlider";
import RecommendedStores from "../components/home/RecommendedStores";
import "../styles/HomePage.css";

export default function HomePage() {
  const [selectedCategory, setSelectedCategory] = useState("전체");
  const [searchQuery, setSearchQuery] = useState("");

  return (
    <div className="home-container">
      {/* 사이드바 */}
      <Sidebar
        selectedCategory={selectedCategory}
        onSelectCategory={setSelectedCategory}
        searchQuery={searchQuery}
      />

      {/* 메인 콘텐츠 */}
      <div className="main-content">
        <SearchBar onSearch={setSearchQuery} />

        {/* 추천 배너 */}
        <section className="banner-section">
          <h2>오늘의 추천 매장</h2>
          <BannerSlider />
        </section>

        {/* 매장 목록 */}
        <section className="store-section">
          <RecommendedStores
            category={selectedCategory}
            searchQuery={searchQuery}
          />
        </section>
      </div>
    </div>
  );
}
