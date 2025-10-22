import React, { useState } from "react";
import Sidebar from "../components/Sidebar";
import SearchBar from "../components/SearchBar";
import BannerSlider from "../components/BannerSlider";
import RecommendedStores from "../components/RecommendedStores";
import "../styles/Home.css";

export default function Home() {
  const [selectedCategory, setSelectedCategory] = useState("전체");
  const [searchQuery, setSearchQuery] = useState("");

  return (
    <div className="home-container">
      <Sidebar
        selectedCategory={selectedCategory}
        onSelectCategory={setSelectedCategory}
        searchQuery={searchQuery}
      />
      <div className="main-content">
        <SearchBar onSearch={setSearchQuery} />
        <BannerSlider />
        <RecommendedStores selectedCategory={selectedCategory} />
      </div>
    </div>
  );
}
