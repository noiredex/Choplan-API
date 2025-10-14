import { useEffect, useState, useRef } from 'react';
import { useNavigate } from 'react-router-dom';
import http from '../api/http';
import '../styles/form.css';
import '../styles/button.css';

export default function ReservationPage() {
    const nav = useNavigate();
    const [loading, setLoading] = useState(false);
    const reservationIdRef = useRef(null);

    const [form, setForm] = useState({
        storeId: 1,
        reservationDatetime: '',
        partySize: 2,
        userName: '',
        contactPhone: '',
        specialRequests: '',
    });

    const onChange = (e) => {
        const { name, value } = e.target;
        setForm(prev => ({...prev, [name]: value }));
    };

    useEffect(() => {
        if (window.IMP && !window.__PORTONE_INIT__) {
            window.IMP.init(import.meta.env.VITE_PORTONE_MERCHANT_CODE);
            window.__PORTONE_INIT__ = true;
            console.log('[PortOne] IMP.init done');
        }
    }, []);

    const handleReserveAndPay = async () => {
        try {
            setLoading(true);

            const draftRes = await http.post('/api/v1/reservations/draft', form);
            const reservationId = draftRes?.data?.reservationId;
            reservationIdRef.current = reservationId;
            if (!reservationId) {
                alert('예약에 실패했습니다. 잠시 후 다시 시도해주세요.');
                return;
            }
            console.log('[DRAFT OK] reservationId =', reservationId);

            // 보증금 결제 인텐트 생성
            console.log('[INTENT call] reservationId =', reservationId);
            const intentRes = await http.post('/api/v1/payments/deposit-intent', {
                reservationId: reservationId
            });
            console.log('[INTENT response]', intentRes);
            const { merchantUid, amount, name } = intentRes.data || {};
            if (!merchantUid || !amount) {
                alert('결제 준비에 실패했습니다.');
                return;
            }
            console.log('[INTENT OK]', { merchantUid, amount, name });

            // portone SDX 결제 창 실행
            const IMP = window.IMP;
            if(!IMP) { 
                alert('결제 모듈 초기화 실패'); 
                return; 
            } 

            IMP.request_pay(
                {
                    pg: 'tosspay', // kg이니시스(html5_inicis), 다날(dnal_tpay), 토스(tosspay) 결제성공
                    pay_method: 'card',
                    merchant_uid: merchantUid,
                    name,
                    amount,
                    buyer_tel: form.contactPhone,
                },
                async (rsp) => {
                    console.info('[ProtOne rsp raw]', rsp);

                    const isApproved =
                        rsp?.success === true || 
                        rsp?.status === 'paid' || 
                        rsp?.status === 'PAY_APPROVED';

                    const hasKeys = !!(rsp?.imp_uid && rsp?.merchant_uid);

                    if (isApproved && hasKeys) {
                        try {
                            console.log('[CONFIRM call]', {
                                reservationId: reservationIdRef.current,
                                impUid: rsp.imp_uid,
                                merchantUid: rsp.merchant_uid,
                            });

                        const confirmResponse = await http.post('/api/v1/payments/confirm', {
                            reservationId: reservationIdRef.current,
                            impUid: rsp.imp_uid,
                            merchantUid: rsp.merchant_uid,
                        });

                        console.log('[CONFIRM OK]', confirmResponse);
                        
                        // 성공 시 즉시 리다이렉트
                        nav('/reservations/success');
                    } catch (e) {
                        console.error('[CONFIRM ERROR]', e);
                        console.error('[CONFIRM ERROR Response]', e.response?.data);
                        console.error('[CONFIRM ERROR Status]', e.response?.status);
                        alert(`결제 승인 확인 중 오류가 발생했습니다: ${e.response?.data || e.message}`);
                    }
                    } else {
                        const reason = rsp?.error_msg || '결제가 완료되지 않았습니다. (사유 미전달)';
                        alert(`결제 실패: ${reason}`);
                    }
                }
            );
        } catch (e) {
            console.error(e);
            alert('예약 또는 결제 중 오류가 발생했습니다.');
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="container">
            <h1 className="h1">식당 예약</h1>

            <label>예약 일시 (예: 2025-09-30T19:00:00)</label>
            <input name="reservationDatetime" value={form.reservationDatetime} onChange={onChange} placeholder="YYYY-MM-DDTHH:mm:ss" />
            <div className="row mt16">
                <div style={{ flex: 1 }}>
                    <label>인원</label>
                    <input type="number" name="partySize" value={form.partySize} onChange={onChange} min={1} />
                </div>
            </div>
            <div className="row mt16">
                <div style={{ flex: 0.4 }}>
                    <label>예약자 성함</label>
                    <input type="text" name="userName" value={form.userName} onChange={onChange} placeholder='홍길동'/>
                </div>
                <div style={{ flex: 0.57 }}>
                    <label>연락처</label>
                    <input name="contactPhone" value={form.contactPhone} onChange={onChange} placeholder="010-0000-0000" />
                </div>
            </div>

            <label className="mt16">요청사항</label>
            <textarea name="specialRequests" rows={4} value={form.specialRequests} onChange={onChange} placeholder="예: 알레르기 여부 등등." />

            <p className="help mt16">보증금 10,000원 선결제 후 예약이 확정됩니다.</p>

            <button className="btn block mt24" disabled={loading} onClick={handleReserveAndPay}> {loading ? '결제 처리 중...' : '토스페이로 결제하기'}
            </button>

            <button className="btn block mt24" onClick={() => nav('/')}>취소
            </button>
        </div>
    );
}

