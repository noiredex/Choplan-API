import { Routes, Route } from "react-router-dom";
import HomePage from "./pages/HomePage";
import StoreDetailPage from "./pages/StoreDetailPage";
import "./App.css";

export default function App() {
  return (
    <Routes>
      <Route path="/" element={<HomePage />} />
      <Route path="/store/:id" element={<StoreDetailPage />} />
    </Routes>
  );
}
