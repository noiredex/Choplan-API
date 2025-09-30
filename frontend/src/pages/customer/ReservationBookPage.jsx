import React, { useState, useMemo } from "react"
import BookingForm from "../../components/customer/BookingForm"
import PaymentSummary from "../../components/common/PaymentSummary"
import DepositPayButton from "../../components/common/DepositPayButton"
import '../../styles/reservation.css'

export default function ReservationBookPage() {
    const [form, setForm] = useState({
        storeId: 'st-2001',
        storeName: '찹플랜식당',
        date: '',
        time: '',
        partySize: 2,
        name: '',
        tel: '',
        memo: ''
    })

    const reservationId = useMemo(() => crypto.randomUUID(), [])
    const depositAmount = Number(import.meta.env.VITE_PAYMENT_DEPOSIT_AMOUNT || 1)

    const valid = form.date && form.time && form.partySize > 0 && form.name && form.tel

    return (
        <div className="container">
            <h2>예약하기</h2>
            <div className="layout">
                <div className="left">
                    <BookingForm form={form} onChange={setForm} />
                </div>
                <div className="right">
                    <PaymentSummary form={form} depositAmount={depositAmount} />
                    <div className="actions">
                        <DepositPayButton
                            reservationId={reservationId}
                            storeId={form.storeId}
                            orderName={`[${form.storeName}] 보증금`}
                            depositAmount={depositAmount}
                            buyer={{ name: form.name, email: '',
                                tel: form.tel
                            }} />
                        {!valid && <p className="hint">예약 정보(이름/연락처/일시)를 먼저 입력하세요.</p>}
                    </div>
                </div>
            </div>
        </div>
    )
}