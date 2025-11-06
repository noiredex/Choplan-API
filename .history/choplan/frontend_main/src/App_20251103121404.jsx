import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Homepage from "./pages/Homepage";
import StoreDetail from "./components/store/StoreDetail";
import ReservationPage from "./pages/ReservationPage";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/main" element={<Homepage />} />
        <Route path="/store/:id" element={<StoreDetail />} />
        <Route path="/reservation" element={<ReservationPage />} />
      </Routes>
    </Router>
  );
}

export default App;
