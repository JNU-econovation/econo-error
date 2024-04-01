import React from "react";
import ReactDOM from "react-dom/client"; // React 18 이상에서 사용
import Modal from "react-modal";
import App from "./App.jsx";
import "./fonts/font.css";

const rootElement = document.getElementById("root");
Modal.setAppElement(rootElement);

// React 18 이상에서의 새로운 루트 생성 및 렌더링 방식
ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);
