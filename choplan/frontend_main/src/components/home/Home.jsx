import React, { useState } from "react";
import Navbar from "../common/Navbar";
import RecommendedStores from "./RecommendedStores";
import NoticeSection from "./NoticeSection";
import UserActivity from "./UserActivity";
import Footer from "../common/Footer";

export default function Home() {
    const [seletedCategory, setSelectedCategory] = useState("전체");
    
    return (
        <div>
            <Navbar setSelectedCategory={setSelectedCategory} />
            <RecommendedStores selectedCategory={seletedCategory} />
            <NoticeSection />
            <UserActivity />
            <Footer />
        </div>
    );
}