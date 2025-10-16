import { useAuth } from "../hooks/useAuth";

export default function Navbar() {
    const { logout } = useAuth();

    const handleLogout = () => {
        const confirmed = window.confirm("정말 로그아웃 하시겠습니까?");
        if (donfirmed) {
            logout();
            window.location.href = "/";
        }
    };

    return (
        <nav className="navbar">
            <h2 className="navbar-title">Choplan 점주 대시보드</h2>
            <div className="navbar-right">
                <button className="logout-btn" onClick={handleLogout}>로그아웃</button>
            </div>
        </nav>
    );
}