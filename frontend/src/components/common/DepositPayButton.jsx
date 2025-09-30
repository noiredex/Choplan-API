import React, { useState } from "react";
import { initPortOne } from "../../library/portone";
import { createDepositIntent, confirmDeposit } from "../../services/api/payments";
import '../../styles/DepositPayButton.css'

export default function DepositPayButton({
    reservationId, 
    storeId, 
    orderName, 
    depositAmount, 
    buyer 
    }) {
    const [loading, setLoading] = useState(false)

    const pay = async () => {
        if (!reservationId) return alert('예약ID가 필요합니다.');
        
        try {
            setLoading(true)

            const intent = await createDepositIntent({
                reservationId,
                storeId,
                orderName,
                depositAmount,
                buyer,
            })

            if (!intent?.merchantUid) {
                throw new Error('잘못된 결제 사전생성 응답입니다.')
            }

            const {
                merchantUid,
                amount,
                orderName: svrOrderName,
                buyer: svrBuyer,
                portone,
            } = intent
            

            const IMP = await initPortOne()
            const pg = import.meta.env.VITE_PORTONE_PG || 'tosspay'

            const rsp = await new Promise((resolve, reject) => {
                IMP.request_pay(
                {
                    pg,
                    pay_method: 'card',
                    merchant_uid: merchantUid,
                    name: svrOrderName ?? orderName ?? '예약 보증금',
                    amount,
                    buyer_email: buyer?.email ?? svrBuyer?.email ?? '',
                    buyer_name: buyer?.name ?? svrBuyer?.name ?? '',
                    buyer_tel: buyer?.tel ?? svrBuyer?.tel ?? '',
                    m_redirect_url: portone?.m_redirect_url ?? '/payment/redirect',
                }, (resp) => 
                    (resp.success ? resolve(resp) : reject(new Error(resp.error_msg || '결제 실패')))
                )
            })

            const confirmed = await confirmDeposit({
                reservationId,
                impUid: rsp.imp_uid,
                merchantUid,
            })

            alert('보증금 결제 완료!')
            return confirmed
        } catch (e) {
            console.error(e)
            alert('결제 실패: ' + e.message)
            throw e
        } finally {
            setLoading(false)
        }
    }

    return (
        <button type="button" className="payBtn" disabled={loading} onClick={pay}>
            {loading ? '결제 처리 중...' : '토스페이로 결제'}
        </button>
    )
}