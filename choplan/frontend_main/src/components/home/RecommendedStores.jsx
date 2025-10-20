import React, { useState } from "react";
import "../../styles/RecommendedStores.css";

const storeList = [
  { id: 1, name: "한남 초밥", desc: "룸 보유 · 주차 가능", category: "초밥", image: "/assets/banner1.jpg" },
  { id: 2, name: "이자카야 마루", desc: "룸 없음 · 예약 필수", category: "이자카야", image: "/assets/banner2.jpg" },
  { id: 3, name: "온더테이블 한식당", desc: "룸 보유 · 최대 6인", category: "한식", image: "/assets/banner3.jpg" },
  { id: 4, name: "라비올리", desc: "양식 · 와인 가능", category: "양식", image: "/assets/banner2.jpg" },
  { id: 5, name: "스시 코우", desc: "초밥 · 콜키지 가능", category: "초밥", image: "/assets/banner3.jpg" },
];

export default function RecommendedStores() {
  const categories = ["전체", "이자카야", "초밥", "한식", "양식"];
  const [selectedCategory, setSelectedCategory] = useState("전체");

  const filteredStores =
    selectedCategory === "전체"
      ? storeList
      : storeList.filter((store) => store.category === selectedCategory);

  return (
    <section className="recommended-stores">
      <h2>추천 매장</h2>

      <div className="category-filter">
        {categories.map((cat) => (
          <button
            key={cat}
            className={`category-btn ${selectedCategory === cat ? "active" : ""}`}
            onClick={() => setSelectedCategory(cat)}
          >
            {cat}
          </button>
        ))}
      </div>

      <div className="store-grid">
        {filteredStores.map((store) => (
          <div key={store.id} className="store-card">
            <img src={store.image} alt={store.name} />
            <h3>{store.name}</h3>
            <p>{store.desc}</p>
            <button>더보기</button>
          </div>
        ))}
      </div>
    </section>
  );
}
