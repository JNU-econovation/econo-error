import React from "react";
import ReactDOM from "react-dom/client";
import Modal from "react-modal";
import App from "./App.jsx";
import "./fonts/font.css";

const rootElement = document.getElementById("root");
Modal.setAppElement(rootElement);

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);
