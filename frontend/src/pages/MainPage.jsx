import { Link } from "react-router-dom";
import '../styles/button.css';

export default function MainPage() {
    return (
        <div className="container center">
            <Link className="btn mt24" to="/user/mypage">마이페이지</Link>
            <Link className="btn mt24" to="/reservations">보증금 결제</Link>
        </div>
    );
}