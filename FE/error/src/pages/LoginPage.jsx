import { useEffect, useState } from "react";
import styled from "styled-components";
import { useNavigate, useSearchParams } from "react-router-dom";
import axios from "axios";

const LoginPage = () => {
  const navigate = useNavigate();
  const [searchParams] = useSearchParams();
  const code = searchParams.get("code");
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  const redirectUri = "https://econo-calendar.com/login";

  useEffect(() => {
    if (code) {
      handleSlackAuth(code);
    }
  }, [code]);

  const handleSlackAuth = async (authCode) => {
    setIsLoading(true);
    setError(null);
    try {
      const response = await axios.post(
        `https://error.econo-calendar.com:8080/api/auth/login/slack?type=slack&code=${authCode}&redirect_uri=https://econo-calendar.com/login`
      );

      if (response.data.code === "201") {
        localStorage.setItem("slackToken", response.data.data.accessToken);
        navigate("/");
      } else {
        setError(response.data.message || "로그인 실패");
        console.error("로그인 실패:", response.data);
      }
    } catch (error) {
      setError("인증에 실패했습니다. 다시 시도해주세요.");
      console.error("Slack 인증 중 오류 발생:", error.response || error);
    } finally {
      setIsLoading(false);
    }
  };

  const handleOnLogin = () => {
    const slackAuthUrl = `https://econovation-2018.slack.com/oauth?client_id=437291124342.7141431332214&scope=chat%3Awrite%2Cchat%3Awrite.customize%2Cchat%3Awrite.public%2Cemoji%3Aread%2Cfiles%3Awrite%2Cincoming-webhook&user_scope=chat%3Awrite%2Cusers.profile%3Aread&redirect_uri=${encodeURIComponent(
      redirectUri
    )}&state=&granular_bot_scope=1&single_channel=0&install_redirect=&tracked=1&team=`;
    window.location.href = slackAuthUrl;
  };

  if (isLoading) {
    return <div>로그인 중...</div>;
  }

  return (
    <>
      <StyledTextArea>
        <h2>
          에러 캘린더에 떨어질
          <br /> 준비 되셨나요?
        </h2>
        <StyledSubTitle>
          에코노베이션 회원이 아니신 경우 로그인이 불가하며
          <br />
          공식 일정만 조회 가능합니다.
        </StyledSubTitle>
        {error && <div style={{ color: "red" }}>{error}</div>}
        <StyledSlackButton onClick={handleOnLogin}>
          <StyledSlackImage src="Slack.png" alt="Slack logo" />
          슬랙으로 로그인
        </StyledSlackButton>
      </StyledTextArea>
      <StyledBackground src="Background.png" alt="Background" />
      <StyledCharacter src="Picture.png" alt="Character" />
    </>
  );
};

export default LoginPage;

const StyledTextArea = styled.div`
  position: absolute;
  top: 20%;
  left: 15%;

  h2 {
    font-family: "Pretendard-bold";
    font-size: 3rem;
    font-weight: 900;
    line-height: 3.7rem;
  }
  h3 {
    line-height: 1.5rem;
  }
`;

const StyledSlackButton = styled.button`
  position: relative;
  padding: 1rem 8.5rem 1rem 10.5rem;
  border-radius: 1rem;
  border: none;
  background-color: #e1e1e1;
  font-size: 1rem;
  color: #6f6f6f;
  font-weight: 700;
  cursor: pointer;
`;

const StyledSlackImage = styled.img`
  left: 8rem;
  bottom: 0.9rem;
  position: absolute;
`;

const StyledSubTitle = styled.h3`
  margin-top: 1.7rem;
  margin-bottom: 10rem;
`;

const StyledBackground = styled.img`
  margin-left: 48%;
  margin-top: 8%;
  height: 35rem;
`;

const StyledCharacter = styled.img`
  position: absolute;
  top: 25%;
  left: 60%;
`;
