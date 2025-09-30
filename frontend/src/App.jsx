import { Routes, Route } from 'react-router-dom'
import MainPage from './pages/customer/MainPage'
import ReservationBookPage from './pages/customer/ReservationBookPage'
import PaymentResult from './pages/customer/PaymentResult'
import ReservationDone from './pages/customer/ReservationDone'


function App() {

  return (
      <Routes>
        <Route path="/" element={<MainPage />} />
        <Route path="/payment/redirect" element={<PaymentResult />} />
        <Route path="/reservations/new" element={<ReservationBookPage />} />
        <Route path="/reservations/done" element={<ReservationDone />} />
        {/* <Route path="/" element={<Protected><Dashboard/></Protected>} /> */}
      </Routes>
  )
}

export default App;
