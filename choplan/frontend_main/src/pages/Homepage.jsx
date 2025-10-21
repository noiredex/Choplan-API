import React, { useState } from "react";
import Sidebar from "../components/common/Sidebar";
import SearchBar from "../components/home/SearchBar";
import BannerSlider from "../components/home/BannerSlider";
import RecommendedStores from "../components/home/RecommendedStores";
import "../styles/HomePage.css";

export default function HomePage() {
  const [selectedCategory, setSelectedCategory] = useState(null);
  const [searchQuery, setSearchQuery] = useState("");

  return (
    <div className="homepage-container">
      <Sidebar
        selectedCategory={selectedCategory}
        onSelectCategory={setSelectedCategory}
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
