import { Link } from "react-router-dom";

export default function SuccessPage() {
    return (
        <div className="container center">
            <h1 className="h1">예약이 완료되었습니다 🎉</h1>
            <p>보증금 결제가 확인되었습니다. 자세한 예약 내역은 마이페이지에서 확인하세요.</p>
            <Link className="btn mt24" to="/">홈으로 가기</Link>
        </div>
    );
}