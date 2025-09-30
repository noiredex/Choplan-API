import React from "react";

export default function PaymentSummary({ form, depositAmount }) {
    return (
        <div className="card">
            <h3>결제 요약</h3>
            <ul className="list">
                <li>매장: <strong>{form.storeName || '선택한 매장'}</strong></li>
                <li>예약일시: <strong>{form.date} {form.time}</strong></li>
                <li>인원: <strong>{form.partySize}명</strong></li>
                <li>예약자: <strong>{form.name}</strong> / {form.tel}</li>
            </ul>
            <div className="total">
                보증금: <strong>{depositAmount.toLocaleString()}원</strong>
            </div>
        </div>
    )
}