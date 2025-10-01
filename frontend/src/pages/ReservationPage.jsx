import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import http from '../api/http';
import '../styles/form.css';
import '../styles/button.css';

export default function ReservationPage() {
    const nav = useNavigate();
    const [form, setForm] = useState({
        storeId: 1,
        reservationDatetime: '',
        partySize: 2,
        contactPhone: '',
        specialRequest: ''
    });
    const [loading, setLoading] = useState(false);

    const onChange = (e) => {
        const { name, value } = e.target;
        setForm(prev => ({...prev, [name]: value }));
    };

    const handleReserveAndPay = async () => {
        try {
            setLoading(true);
            const draftRes = await http.post('/api/v1/reservations/draft', form);
            const reservationId = draftRes.data.reservationId;

            // 보증금 결제 인텐트 생성
            const intentRes = await http.post('/api/v1/payments/deposit-intent');
            const { merchantUid, amount, name } = intentRes.data;

            // portone SDX 결제 창 실행
            const { IMP } = window;
            if(!IMP) { alert('결제 모듈 초기화 실패'); return; }
            IMP.init(import.meta.env.VITE_PORTONE_MERCHANT_CODE);

            IMP.request_pay({
                pg: 'tosspay',
                pay_method: 'card',
                merchant_uid: merchantUid,
                name,
                amount,
                buyer_tel: form.contactPhone,
            }, async (rsp) => {
                if (rsp.success) {
                    await http.post('/api/v1/payments/confirm', {
                        reservationId,
                        impUid: rsp.imp_uid,
                        merchantUid: rsp.merchant_uid,
                    });
                    nav('/reservations/success');
                } else {
                    alert(`결제 실패: ${rsp.error_msg}`);
                }
            });
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
            <textarea name="specialRequest" rows={4} value={form.specialRequest} onChange={onChange} placeholder="예: 알레르기 여부 등등." />

            <p className="help mt16">보증금 10,000원 선결제 후 예약이 확정됩니다.</p>

            <button className="btn block mt24" disabled={loading} onClick={handleReserveAndPay}> {loading ? '결제 처리 중...' : '토스페이로 결제하기'}
            </button>
        </div>
    );
}
