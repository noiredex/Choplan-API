import React, { useEffect, useState } from "react";
import "../../styles/RecommendedStores.css";

export default function RecommendedStores({ category, searchQuery }) {
  const [stores, setStores] = useState([]);

  // ✅ 예시 데이터 (실제 API 연결 시 이 부분 대체)
  const allStores = [
    { id: 1, name: "한남 초밥", category: "초밥", desc: "룸 보유 · 주차 가능", img: "https://placehold.co/300x200" },
    { id: 2, name: "이자카야 마루", category: "이자카야", desc: "룸 없음 · 예약 필수", img: "https://placehold.co/300x200" },
    { id: 3, name: "명동 한식당", category: "한식", desc: "전통 한식 · 좌식 가능", img: "https://placehold.co/300x200" },
    { id: 4, name: "동남아 포차", category: "동남아", desc: "분위기 좋은 포차", img: "https://placehold.co/300x200" },
  ];

  useEffect(() => {
    let filtered = allStores;

    // 검색어 반영
    if (searchQuery) {
      filtered = filtered.filter((store) =>
        store.name.toLowerCase().includes(searchQuery.toLowerCase())
      );
    }

    // 카테고리 반영
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
                <p>{store.desc}</p>
                <button>더보기</button>
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
