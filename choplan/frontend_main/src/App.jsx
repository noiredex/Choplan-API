import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import StoreDetail from "./pages/StoreDetail";
import "./App.css";

export default function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/store/:id" element={<StoreDetail />} />
      </Routes>
    </Router>
  );
}
