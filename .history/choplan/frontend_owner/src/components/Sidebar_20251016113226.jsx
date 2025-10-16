import { NavLink } from "react-router-dom";

export default function Sidebar() {
  return (
    <aside className="sidebar">
      <ul>
        <li>
          <NavLink
            to="/dashboard"
            className={({ isActive }) => (isActive ? "active-link" : "")}
          >
            대시보드
          </NavLink>
        </li>
        <li>
          <NavLink
            to="/store"
            className={({ isActive }) => (isActive ? "active-link" : "")}
          >
            매장 관리
          </NavLink>
        </li>
        <li>
          <NavLink
            to="#"
            onClick={(e) => {
              e.preventDefault();
              alert("예약 내역 기능은 아직 연동되지 않았습니다.");
            }}
          >
            예약 내역
          </NavLink>
        </li>
        <li>
          <NavLink
            to="#"
            onClick={(e) => {
              e.preventDefault();
              alert("리뷰 관리 기능은 아직 연동되지 않았습니다.");
            }}
          >
            리뷰 관리
          </NavLink>
        </li>
      </ul>
    </aside>
  );
}
