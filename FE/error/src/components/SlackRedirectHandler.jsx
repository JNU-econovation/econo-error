import axios from "axios";
import React, { useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";

const SlackRedirectHandler = () => {
  const location = useLocation();
  useEffect(() => {
    const getQueryParam = (param) => {
      const urlParams = new URLSearchParams(location.search);
      return urlParams.get(param);
    };

    const authorizationCode = getQueryParam("code");
    if (authorizationCode) {
      const sendCodeToServer = async () => {
        try {
          const response = await fetch("/api/auth/login/slack", {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify({ code: authorizationCode }),
          });
          const data = await response.json();
          console.log(data);
        } catch (error) {
          console.error("Error:", error);
        }
      };
      sendCodeToServer();
    }
  }, [location]);

  return <div>Redirecting...</div>;
};

export default SlackRedirectHandler;
