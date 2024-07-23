import axios from "axios";
import { useNavigate } from "react-router-dom";

// Axios 기본 설정
axios.defaults.baseURL = import.meta.env.VITE_ERROR_API;
axios.defaults.headers.common["Content-Type"] = "application/json";
axios.defaults.withCredentials = true;

let isRefreshing = false;
let refreshSubscribers = [];

const onRefreshed = (token) => {
  refreshSubscribers.map((cb) => cb(token));
};

const addRefreshSubscriber = (cb) => {
  refreshSubscribers.push(cb);
};

axios.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("slackToken");
    if (token) {
      config.headers["Authorization"] = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

axios.interceptors.response.use(
  (response) => {
    return response;
  },
  async (error) => {
    const originalRequest = error.config;
    if (error.response.status === 401 && !originalRequest._retry) {
      if (isRefreshing) {
        return new Promise((resolve) => {
          addRefreshSubscriber((token) => {
            originalRequest.headers["Authorization"] = "Bearer " + token;
            resolve(axios(originalRequest));
          });
        });
      }

      originalRequest._retry = true;
      isRefreshing = true;

      const refreshToken = localStorage.getItem("refreshToken");

      return new Promise((resolve, reject) => {
        axios
          .post("/auth/reissue", { refreshToken: refreshToken })
          .then(({ data }) => {
            const newToken = data.data.accessToken;
            localStorage.setItem("slackToken", newToken);
            axios.defaults.headers.common["Authorization"] =
              "Bearer " + newToken;
            originalRequest.headers["Authorization"] = "Bearer " + newToken;
            onRefreshed(newToken);
            resolve(axios(originalRequest));
          })
          .catch((err) => {
            reject(err);
            useNavigate().push("/login");
          })
          .finally(() => {
            isRefreshing = false;
            refreshSubscribers = [];
          });
      });
    }
    return Promise.reject(error);
  }
);

export default axios;
