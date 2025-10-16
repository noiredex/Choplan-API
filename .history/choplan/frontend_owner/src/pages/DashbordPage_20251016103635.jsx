// 대시보드 페이지
import { useEggect, useState } from "react";
import { fetchOwnerProfile } from "../api/ownerApi";
import {useAuth } from "../hooks/useAuth";

export default function DashbordPage() {
    const { logout } = useAuth();
    const [owner, setOwner] = useState(null);

    useEffect(() => {
        fetchOwnerProfile()
        .then(setOwner)
        .catch(() => {
            alert("세션이 만료되었습니다. 다시 로그인하세요.");
            logout();
            window.location.herf = "/";
        });
    }, []);

    if (!owner) return <p> 로딩 중...</p>;

    retuen (
        <div>
            <h1>
                {owner.name} 님의 점주 대시보드
            </h1>
            <p>이메일: {owner.email}</p>
            <p>상호명: {owner.storeName}</p>
            <button onClick={logout}>로그아웃</button>
        </div>
    );
}