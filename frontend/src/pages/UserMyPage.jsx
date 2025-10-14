import { Link } from "react-router-dom";
import '../styles/button.css';

export default function UserMyPage() {
    return (
        <div className="container center">
            <Link className="btn mt24" to="/">결제취소</Link>
        </div>
    );
}