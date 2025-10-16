import { useEffect, useState } from "react";
import Navbar from "../components/Navbar";
import Sidebar from "../components/Sidebar";
import LoadingSpinner from "../components/LoadingSpinner";
import { fetchOwnerProfile } from "../api/ownerApi";

export default function StoreManagePage() {
  const [owner, setOwner] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const loadData = async () => {
      try {
        const data = await fetchOwnerProfile();
        setOwner(data);
      } catch (error) {
        alert("로그인 세션이 만료되었거나 데이터를 불러올 수 없습니다.");
        window.location.href = "/";
      } finally {
        setLoading(false);
      }
    };
    loadData();
  }, []);

  if (loading) return <LoadingSpinner text="매장 정보를 불러오는 중..." />;

  return (
    <div className="dashboard-container">
      <Navbar />
      <div className="main-content">
        <Sidebar />
        <div className="page-content">
          <h1>매장 관리</h1>
          <p>점주명: {owner.name}</p>
          <p>매장명: {owner.storeName}</p>
          <p>전화번호: {owner.storePhone}</p>
          <p>상태: {owner.ownerStatus}</p>
        </div>
      </div>
    </div>
  );
}
