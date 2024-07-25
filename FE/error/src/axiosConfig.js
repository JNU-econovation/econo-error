import axios from "axios";

// Axios 기본 설정
axios.defaults.baseURL = import.meta.env.VITE_ERROR_API;
axios.defaults.headers.common["Content-Type"] = "application/json";
axios.defaults.withCredentials = true;
