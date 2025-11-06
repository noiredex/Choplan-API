import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "../../styles/RecommendedStores.css";

export default function RecommendedStores({ category, searchQuery }) {
  const [stores, setStores] = useState([]);
  const navigate = useNavigate();

  const allStores = [
    {
      id: 1,
      name: "오스테리아 오르조",
      category: "이탈리안",
      desc: "한남 · 이탈리아 음식",
      rating: 4.6,
      reviews: 6365,
      price: "점심/저녁 1–8만원",
      hours: "17:30 영업 시작 (브레이크타임 있음)",
      img: "/images/store1.jpg",
    },
    {
      id: 2,
      name: "스시 마루",
      category: "초밥",
      desc: "룸 보유 · 주차 가능",
      rating: 4.8,
      reviews: 4231,
      price: "런치 3만원 / 디너 7만원",
      hours: "11:30 – 22:00",
      img: "/images/store2.jpg",
    },
    {
      id: 3,
      name: "소이연남",
      category: "태국식",
      desc: "분위기 좋은 포차",
      rating: 4.5,
      reviews: 2880,
      price: "평균 2–4만원",
      hours: "11:00 – 21:30",
      img: "/images/store3.jpg",
    },
  ];

  useEffect(() => {
    let filtered = allStores;

    if (searchQuery) {
      filtered = filtered.filter((store) =>
        store.name.toLowerCase().includes(searchQuery.toLowerCase())
      );
    }

    if (category && category !== "전체") {
      filtered = filtered.filter((store) => store.category === category);
    }

    setStores(filtered);
  }, [category, searchQuery]);

  return (
    <div className="recommended-stores">
      <h2>{category ? category : "전체"} 매장</h2>

      <div className="store-grid">
        {stores.length > 0 ? (
          stores.map((store) => (
            <div className="store-card" key={store.id}>
              <img src={store.img} alt={store.name} />
              <div className="store-info">
                <h3>{store.name}</h3>
                <p className="store-desc">{store.desc}</p>
                <div className="store-meta">
                  <span className="rating">⭐ {store.rating}</span>
                  <span className="reviews">({store.reviews.toLocaleString()} 리뷰)</span>
                </div>
                <p className="store-hours">{store.hours}</p>
                <p className="store-price">{store.price}</p>
                <button onClick={() => navigate(`/store/${store.id}`)}>더보기</button>
              </div>
            </div>
          ))
        ) : (
          <p className="no-results">검색 결과가 없습니다.</p>
        )}
      </div>
    </div>
  );
}
