import React from 'react'
import '../styles/cards.css'

export default function MyReservationCard({ item, onCancel, onCancelDeposit }){
    return (
        <div className="reservation-card">
            <div className="thumb"/>
            <div className="meta">
                <div className="name">{item.storeName}</div>
                <div className="subtle">{item.date} • {item.people}명 • {item.status}</div>
                <div className="row" style={{marginTop:8}}>
                    <button className="btn btn-ghost" onClick={()=>onCancel(item.id)}>예약 취소</button>
                    <button className="btn btn-outline" onClick={()=>onCancelDeposit(item.id)}>보증금 결제취소</button>
                    <span className="badge">테스트용</span>
                </div>
            </div>
        </div>
    )
}