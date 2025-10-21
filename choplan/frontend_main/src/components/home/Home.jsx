import React, { useState } from "react";
import Navbar from "../common/Navbar";
import RecommendedStores from "./RecommendedStores";
import NoticeSection from "./NoticeSection";
import UserActivity from "./UserActivity";
import Footer from "../common/Footer";

export default function Home() {
  const [selectedCategory, setSelectedCategory] = useState("전체");

  const handleCategoryChange = (category) => {
    setSelectedCategory(category);
  };

  return (
    <div>
      <Navbar selectedCategory={selectedCategory} onCategoryChange={handleCategoryChange} />
      <RecommendedStores selectedCategory={selectedCategory} onCategoryChange={handleCategoryChange} />
      <NoticeSection />
      <UserActivity />
      <Footer />
    </div>
  );
}
