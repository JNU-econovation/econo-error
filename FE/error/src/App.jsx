import "./App.css";
import { Routes, Route } from "react-router-dom";
import MainPage from "./components/pages/MainPage";
import CalendarModify from "./components/pages/CalendarModify";

function App() {
  return (
    <Routes>
      <Route path="/" element={<MainPage />} />
      <Route path="/ModifyPage" element={<CalendarModify />} />
    </Routes>
  );
}

export default App;
