import { Routes, Route } from "react-router-dom";
import MainPage from "./pages/MainPage";
import LoginPage from "./pages/LoginPage";
import CalendarModify from "./pages/CalendarModify";
import "./axiosConfig";

function App() {
  return (
    <Routes>
      <Route path="/" element={<MainPage />} />
      <Route path="/ModifyPage" element={<CalendarModify />} />
      <Route path="/login" element={<LoginPage />} />
    </Routes>
  );
}

export default App;
