import styled from "styled-components";
import { FaCheck } from "react-icons/fa";

import { useState } from "react";

const PublicFilter = () => {
  const [publicClicked, setPublicClicked] = useState(false);
  const [WeeklyClicked, setWeeklyClicked] = useState(false);
  const handlePublicClick = () => {
    if (publicClicked === false) setPublicClicked(true);
    else setPublicClicked(false);
  };
  const handleWeeklyClick = () => {
    if (WeeklyClicked === false) setWeeklyClicked(true);
    else setWeeklyClicked(false);
  };
  return (
    <PublicFilterFrame>
      <div style={{ color: "rgb(60, 64, 67)", marginBottom: "0.5rem" }}>
        에코노 캘린더
      </div>
      <div>
        <PublicBtn clicked={publicClicked} onClick={handlePublicClick}>
          <FaCheck size="0.6rem" />
        </PublicBtn>
        공식행사
      </div>
      <div>
        <WeeklyBtn clicked={WeeklyClicked} onClick={handleWeeklyClick}>
          <FaCheck size="0.6rem" />
        </WeeklyBtn>
        주간발표
      </div>
    </PublicFilterFrame>
  );
};

export default PublicFilter;

const PublicFilterFrame = styled.div`
  margin: 1.3rem;
`;
const PublicBtn = styled.button`
  border: none;
  background-color: #ff5b5b;
  margin: 0.4rem;
  color: white;
  border: 0.15rem solid #ff5b5b;
  border-radius: 0.15rem;
  ${({ clicked }) =>
    clicked &&
    `background-color: transparent;
    color: transparent;
    border: 0.15em solid #ff5b5b;
  `}
`;
const WeeklyBtn = styled.button`
  border: none;
  background-color: #63abff;
  border: 0.15em solid #63abff;
  color: aliceblue;
  border-radius: 0.15rem;
  margin: 0.4rem;
  ${({ clicked }) =>
    clicked &&
    `background-color: transparent;
    color: transparent;
    border: 0.15em solid #63abff;
  `}
`;
