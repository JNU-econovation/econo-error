import styled from "styled-components";

export const WelcomeLoginContainer = styled.div`
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

export const SlackBtn = styled.button`
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

export const SlackImage = styled.img`
  left: 8rem;
  bottom: 0.9rem;
  position: absolute;
`;

export const InfoText = styled.h3`
  margin-top: 1.7rem;
  margin-bottom: 10rem;
`;

export const BackgroundImg = styled.img`
  margin-left: 48%;
  margin-top: 8%;
  height: 35rem;
`;

export const CharacterImg = styled.img`
  position: absolute;
  top: 25%;
  left: 60%;
`;

export const LoadingContainer = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: rgba(255, 255, 255, 0.8);
`;

export const LoadingImage = styled.img`
  width: 100px;
  height: auto;
`;
