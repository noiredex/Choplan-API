import './styles/base.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import ReservationPage from './pages/ReservationPage';
import SuccessPage from './pages/SuccessPage';

function App() {

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<ReservationPage />} />
        <Route path="/reservations/success" element={<SuccessPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
