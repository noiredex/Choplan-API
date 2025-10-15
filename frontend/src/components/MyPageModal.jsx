import React, { useEffect, useState } from 'react'
import '../styles/modal.css'
import { useNavigate } from 'react-router-dom'
import { api } from '../api/client'
import MyReservationCard from './MyReservationCard';
import MyReviewItem from './MyReviewItem';
import MyScrapItem from './MyScrapItem';


export default function MyPageModal( { onClose }) {
    const nav = useNavigate();
    const [tab, setTab] = useState('reservations');
    const [summary, setSummary] = useState(null);
    const [reservations, setReservations] = useState([]);
    const [reviews, setReviews] = useState([]);
    const [scraps, setScraps] = useState([]);

    useEffect(()=>{
        api.mypage.summary().then(setSummary).catch(() => {})
        api.mypage.reservations().then(setReservations).catch(() => {})
        api.mypage.reviews().then(setReviews).catch(() => {})
        api.mypage.scraps().then(setScraps).catch(() => {})
    },[]);

    const onCancel = async(id) => {
        await api.reservation.cancel(id);
        setReservations(prev => prev.map(x => x.id === id ? { ...x, status: 'CANCELED' } : x));
    }

    const onCancelDeposit = async(id) => {
        await api.reservation.cancelDeposit(id);
        alert('보증금 결제취소 요청 완료(테스트)');
    }

    return (
        <div className='modal-backdrop' onClick={onClose}>
            <div className='modal' onClick={e => e.stopPropagation()}>
                <div className='modal-header'>
                    <div className='modal-title'>마이페이지</div>
                    <div className='row'>
                        <button className='btn btn-ghost' onClick={() => nav('user/mypage/profile/edit')}>프로필 수정</button>
                        <button className='btn btn-ghost' onClick={onClose}>닫기</button>
                    </div>
                </div>

                <div className='modal-body'>
                    <div className='card' style={{padding:12, marginBottom:12}}>
                        <div className='row' style={{alignItems:'center'}}>
                            <div className='profile-avatar' />
                            <div style={{marginLeft:12}}>
                                <div style={{fontWeight:700}}>{summary?.nickname ?? '게스트'}</div>
                                <div className='subtle'>최근 방문 {summary?.recentVisited ?? 0}곳</div>
                            </div>
                        </div>
                    </div>

                    <div className='row' style={{gap:8, marginBottom:12}}>
                        <button className={"btn "+(tab==='reservations'? 'btn-wine':'btn-ghost')} onClick={()=>setTab('reservations')}>내 예약조회</button>
                        <button className={"btn "+(tab==='reviews'? 'btn-wine':'btn-ghost')} onClick={()=>setTab('reviews')}>내 리뷰 조회</button>
                        <button className={"btn "+(tab==='scraps'? 'btn-wine':'btn-ghost')} onClick={()=>setTab('scraps')}>내 스크랩 조회</button>
                    </div>

                    {tab==='reservations' && (
                        <div className='card' style={{overflow:'hidden'}}>
                            {reservations.length === 0 && <div className='center' style={{height:120}}><span className='subtle'>예약 내역이 없습니다.</span></div>}
                            {reservations.map(r => (
                                <MyReservationCard key={r.id} item={r} onCancel={onCancel} onCancelDeposit={onCancelDeposit}/>
                            ))}
                        </div>
                    )}

                    {tab==='reviews' && (
                        <div className='card' style={{padding:12}}>
                            {reviews.length === 0 && <div className='center' style={{height:120}}><span className='subtle'>리뷰 내역이 없습니다.</span></div>}
                            {reviews.map(v => <MyReviewItem key={v.id} review={v} />)}
                        </div>
                    )}

                    {tab==='scraps' && (
                        <div className='card' style={{padding:12}}>
                            {scraps.length === 0 && <div className='center' style={{height:120}}><span className='subtle'>스크랩 내역이 없습니다.</span></div>}
                            {scraps.map(s => <MyScrapItem key={s.id} scrap={s} />)}
                        </div>
                    )}
                </div>    
            </div>
        </div>
    )
}
