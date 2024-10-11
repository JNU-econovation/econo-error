import { useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";

const SlackRedirectHandler = () => {
  const location = useLocation();

  const urlParams = new URLSearchParams();
  const code = urlParams.get("code");
  console.log(code);
  useEffect(() => {
    const getQueryParam = (code) => {
      return urlParams.get("code");
    };

    const authorizationCode = getQueryParam("code");

    if (authorizationCode) {
      const sendCodeToServer = async () => {
        try {
          const response = await fetch("/api/auth/login", {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify({ code: authorizationCode }),
          });
          const data = await response.json();
          console.log(data);
          // 로그인 성공 후 처리 (예: 토큰 저장, 메인 페이지로 리다이렉트)
        } catch (error) {
          console.error("Error:", error);
          // 에러 처리 (예: 에러 페이지로 리다이렉트)
        }
      };
      sendCodeToServer();
    }
  }, [location]);

  return <div>로그인 처리 중...</div>;
};

export default SlackRedirectHandler;
