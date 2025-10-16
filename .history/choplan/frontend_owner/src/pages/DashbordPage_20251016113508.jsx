// 대시보드 페이지
import { useEffect, useState } from "react";
import { useAuth } from "../hooks/useAuth";
import Navbar from "../components/Navbar";
import Sidebar from "../components/Sidebar";
import LoadingSpinner from "../components/LoadingSpinner";
import { fetchOwnerProfile } from "../api/ownerApi";

export default function DashboardPage() {
  const { logout } = useAuth();
  const [owner, setOwner] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const loadData = async () => {
      try {
        const data = await fetchOwnerProfile();
        setOwner(data);
      } catch (err) {
        alert("세션이 만료되었습니다. 다시 로그인해주세요.");
        logout();
        window.location.href = "/";
      } finally {
        setLoading(false);
      }
    };
    loadData();
  }, []);

  if (loading) return <LoadingSpinner text="대시보드를 불러오는 중..." />;

  return (
    <div className="dashboard-container">
      <Navbar />
      <div className="main-content">
        <Sidebar />
        <div className="page-content">
          <h1>대시보드</h1>
          <p>점주명: {owner.name}</p>
          <p>매장명: {owner.storeName}</p>
          <p>현재 상태: {owner.ownerStatus}</p>
        </div>
      </div>
    </div>
  );
}
