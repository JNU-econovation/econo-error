import styled from "styled-components";

const LoginPage = () => {
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
        <StyledSlackButton>
          <StyledSlackImage src="Slack.png"></StyledSlackImage>슬랙으로 로그인
        </StyledSlackButton>
      </StyledTextArea>
      <StyledBackground src="Background.png" />
      <StyledCharacter src="Picture.png" />
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
