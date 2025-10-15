import React from 'react'
import { useNavigate } from 'react-router-dom';

export default function HomePage( { onOpenMy } ) {
    const nav = useNavigate();

    return (
        <div>
            <h2 className='section-title'>홈</h2>
            <p className='subtle'>메인 테스트 페이지</p>
            <div style={{marginTop:16, gap:8, display:'flex'}}>
                <button className='btn btn-wine' onClick={onOpenMy} >마이페이지 열기
                </button>
                <button className='btn btn-wine' onClick={() => nav('/reservations')} > 예약 페이지로 이동
                </button>
            </div>
        </div>
    )
}
