import React from "react";
import MainLayout from "../components/layout/MainLayout";
import HeroSection from "../components/home/HeroSection";
import NoticeSection from "../components/home/NoticeSection";
import RecommendedStores from "../components/home/RecommendedStores";
import UserActivity from "../components/home/UserActivity";
import "../styles/HomePage.css";

export default function HomePage() {
  return (
    <MainLayout>
      <div className="home-page">
        <HeroSection />
        <NoticeSection />
        <RecommendedStores />
        <UserActivity />
      </div>
    </MainLayout>
  );
}
