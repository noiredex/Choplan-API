import React, { useState } from "react";
import Sidebar from "../common/Sidebar";
import SearchBar from "./SearchBar";
import BannerSlider from "./BannerSlider";
import RecommendedStores from "./RecommendedStores";
import "../../styles/Home.css";

export default function Home() {
  const [selectedCategory, setSelectedCategory] = useState("전체");
  const [searchQuery, setSearchQuery] = useState("");

  return (
    <div className="home-container">
      <Sidebar
        selectedCategory={selectedCategory}
        onSelectCategory={setSelectedCategory}
        searchQuery={searchQuery}  // 양방향 동기화
      />

      <div className="main-content">
        <SearchBar onSearch={setSearchQuery} />
        <BannerSlider />
        <RecommendedStores
          category={selectedCategory}
          searchQuery={searchQuery}
        />
      </div>
    </div>
  );
}
