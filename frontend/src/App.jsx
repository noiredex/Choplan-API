import './styles/base.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import ReservationPage from './pages/ReservationPage';
import SuccessPage from './pages/SuccessPage';
import MainPage from './pages/MainPage';
import UserMyPage from './pages/UserMyPage';

function App() {

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<MainPage />} />
        <Route path="/reservations" element={<ReservationPage />} />
        <Route path="/reservations/success" element={<SuccessPage />} />
        <Route path="/user/mypage" element={<UserMyPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
