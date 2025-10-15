import React from "react";
import '../styles/bottom-nav.css';
import { NavLink } from "react-router-dom";

export default function BottomNav( {onOpenMy} ) {
    return (
        <nav className="bottom-nav">
            <div className="inner">
                <NavLink to="/" className={({isActive})=> isActive? 'active':''}>
                    <div className="icon"/>
                    <span>홈</span>
                </NavLink>
                <NavLink to="/search" className={({isActive})=> isActive? 'active':''}>
                    <div className="icon"/>
                    <span>검색</span>
                </NavLink>
                <NavLink to="/map" className={({isActive})=> isActive? 'active':''}>
                    <div className="icon"/>
                    <span>지도</span>
                </NavLink>
                <NavLink to="/notifications" className={({isActive})=> isActive? 'active':''}>
                    <div className="icon"/>
                    <span>알림</span>
                </NavLink>
                <a onClick={onOpenMy} style={{cursor:'pointer'}}>
                    <div className="icon"/>
                    <span>마이페이지</span>
                </a>
                {/* <Link to="/">
                    <div className="icon" />
                    <span>홈</span>
                </Link>
                <Link to="/map">
                    <div className="icon" />
                    <span>지도</span>
                </Link>
                <Link to="/search">
                    <div className="icon" />
                    <span>검색</span>
                </Link>
                <Link to="/user/mypage">
                    <div className="icon" />
                    <span>마이페이지</span>
                </Link>
                <Link to="/notifications">
                    <div className="icon" />
                    <span>알림</span>
                </Link> */}
            </div>
        </nav>
    )
}
