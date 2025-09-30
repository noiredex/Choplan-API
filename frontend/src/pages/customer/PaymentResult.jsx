import React, { useEffect, useState } from "react";
import { useSearchParams, useNavigate } from 'react-router-dom'
import { confirmDeposit } from "../../services/api/payments";


export default function PaymentResult() {
    const [sp] = useSearchParams()
    const nav = useNavigate()
    const [msg, setMsg] = useState('결제 확인 중...')

    useEffect(() => {
        const impUid = sp.get('imp_uid')
        const merchantUid = sp.get('merchant_uid')
        const reservationId = sp.get('reservationId')
        if (!impUid || !merchantUid || !reservationId) {
            setMsg('파라미터가 부족합니다')
            return
        }
        confirmDeposit({ reservationId, impUid, merchantUid })
        .then(() => { setMsg('결제가 확인되었습니다.'); setTimeout(() => nav('/reservations/done'), 800) })
        .catch(e => setMsg('실패: '+e.message))
    }, [])

    return <div style={{padding:24}}>{msg}</div>
}