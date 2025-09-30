import React from "react";

export default function BookingForm({ form, onChange }) {
    const set = (k) => (e) => onChange({ ...form, [k]: e.target.value })
    return (
        <div className="card">
            <h3>예약 정보</h3>
            <div className="grid">
                <label>
                    날짜
                    <input type="date" value={form.date} onChange={set('date')} />
                </label>
                <label>
                    시간
                    <input type="time" value={form.time} onChange={set('time')} />
                </label>
                <label>
                    인원
                    <input type="number" min={1} max={20} value={form.partySize} onChange={set('partySize')} />
                </label>
            </div>
            <div className="grid">
                <label>
                    예약자 이름
                    <input value={form.name} onChange={set('name')} placeholder="찹플랜" />
                </label>
                <label>
                    연락처
                    <input value={form.tel} onChange={set('tel')} placeholder="010-0000-0000" />
                </label>
            </div>
            <label>
                요청사항(선택)
                <textarea value={form.memo} onChange={set('memo')} rows={3} />
            </label>
        </div>
    )
}