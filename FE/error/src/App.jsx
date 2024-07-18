import { Routes, Route } from "react-router-dom";
import MainPage from "./pages/MainPage";
import LoginPage from "./pages/LoginPage";
import CalendarModify from "./pages/CalendarModify";
import "./axiosConfig";
import SlackRedirectHandler from "./components/SlackRedirectHandler";

function App() {
  return (
    <Routes>
      <Route path="/" element={<MainPage />} />
      <Route path="/ModifyPage" element={<CalendarModify />} />
      <Route path="/login" element={<LoginPage />} />
      <Route path="/slack-redirect" element={<SlackRedirectHandler />} />
    </Routes>
  );
}

export default App;
