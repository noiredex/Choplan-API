import React, { useEffect, useState } from "react";
import { api } from "../api/client";

export default function ProfileEditPage() {
    const [form, setForm] = useState({ nickname: '', birthday: '', favorites: '', memo: '' });
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        api.profile.get().then(res => {
            setForm({
                nickname: res.nickname ?? '',
                birthday: res.birthday ?? '',
                favorites: res.favorites ?? '',
                memo: res.memo ?? ''
            });
        }).finally(() => setLoading(false));
    }, []);

    const onSubmit =async (e) => {
        e.preventDefault();
        await api.profile.update(form);
        alert('프로필이 수정되었습니다.');
    }

    if (loading) return <div className="center" style={{height:200}}>로딩중...</div>

    return (
        <div>
            <h2 className="section-title">프로필 수정</h2>
            <form className="card" style={{padding:16}} onSubmit={onSubmit}>
                <div className="row" style={{alignItems:'center'}}>
                    <div className="profile-avatar"/>
                    <button type="button" className="btn btn-ghost" style={{marginLeft:12}}>이미지 변경</button>
                </div>

                <div className="form-grid" style={{marginTop:12}}>
                    <div>
                        <label className="subtle">닉네임</label>
                        <input className="input" value={form.nickname} onChange={e=>setForm({...form, nickname:e.target.value})} />
                    </div>
                    <div>
                        <label className="subtle">생일/기념일 등록</label>
                        <input className="input" placeholder="YYYY-MM-DD" value={form.birthday} onChange={e=>setForm({...form, birthday:e.target.value})} />
                    </div>
                    <div>
                        <label className="subtle">내 알레르기 정보</label>
                        <input className="input" value={form.favorites} onChange={e=>setForm({...form, favorites:e.target.value})} />
                    </div>
                    <div>
                        <label className="subtle">메모</label>
                        <textarea className="textarea" rows={4} value={form.memo} onChange={e=>setForm({...form, memo:e.target.value})} />
                    </div>
                    </div>
                    <div className="form-actions">
                        <button type="submit" className="btn btn-wine">저장</button>
                    </div>
            </form>
        </div>
    )
}