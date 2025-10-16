import Navbar from "../components/Navbar";
import Sidebar from "../components/Sidebar";

export default function StoreManagePage() {
    return (
        <div className="dashboard-container">
            <Navbar />
            <div className="main-content">
                <Sidebar />
                <div className="page-content">
                    <h1>매장 관리 페이지</h1>
                    <p>이곳에서 점주는 매장 정보를 수정하거나 관리 할 수 있습니다.</p>
                </div>
            </div>
        </div>
    );
}