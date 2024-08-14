import { useEffect, useState } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";
import axios from "axios";
import * as S from "../styles/pages/LoginPage";
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
    return (
      <S.LoadingContainer>
        <S.LoadingImage src="/image80.png" alt="Loading" />
      </S.LoadingContainer>
    );
  }

  return (
    <>
      <S.WelcomeLoginContainer>
        <h2>
          에러 캘린더에 떨어질
          <br /> 준비 되셨나요?
        </h2>
        <S.InfoText>
          에코노베이션 회원이 아니신 경우 로그인이 불가하며
          <br />
          공식 일정만 조회 가능합니다.
        </S.InfoText>
        {error && <div style={{ color: "red" }}>{error}</div>}
        <S.SlackBtn onClick={handleOnLogin}>
          <S.SlackImage src="Slack.png" alt="Slack logo" />
          슬랙으로 로그인
        </S.SlackBtn>
      </S.WelcomeLoginContainer>
      <S.BackgroundImg src="Background.png" alt="Background" />
      <S.CharacterImg src="Picture.png" alt="Character" />
    </>
  );
};

export default LoginPage;
