import React, { useState } from 'react';
import { Routes, Route } from 'react-router-dom';
import HomePage from './pages/HomePage';
import MyPageModal from './components/MyPageModal';
import MapPage from './pages/MapPage';
import SearchPage from './pages/SearchPage';
import NotificationsPage from './pages/NotificationsPage';
import ProfileEditPage from './pages/ProfileEditPage';
import ReservationPage from './pages/ReservationPage';
import SuccessPage from './pages/SuccessPage';
import BottomNav from './components/BottomNav';

export default function App() {
  const [myOpen, setMyOpen] = useState(false);

  return (
    <>
      <div className='topbar'>
        <div className='topbar-inner'>
          <div className='topbar-title'>Choplan</div>
          <div className='topbar-actions'>
            <a className='link' href='#'>공지사항</a>
            <a className='link' href='#'>고객센터</a>
          </div>
        </div>
      </div>

      <div className='container'>
          <Routes>
            <Route path="/" element={<HomePage onOpenMy={() => setMyOpen(true)} />} />
            <Route path='/map' element={<MapPage />} />
            <Route path='/search' element={<SearchPage />} />
            <Route path='/notifications' element={<NotificationsPage />} />
            <Route path="/user/mypage/profile/edit" element={<ProfileEditPage />} />
            <Route path="/reservations" element={<ReservationPage />} />
            <Route path="/reservations/success" element={<SuccessPage />} />
            <Route path="/user/mypage" element={<MyPageModal />} />
          </Routes>         
      </div>

      <BottomNav onOpenMy={() => setMyOpen(true)} />  
      {myOpen && <MyPageModal onClose={() => setMyOpen(false)} />}
    </>
  );
}
