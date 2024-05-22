import styled from "styled-components";
import ScheduleToggle from "../ScheduleToggle";

const PublicFilter = () => {
  return (
    <PublicFilterFrame>
      <div
        style={{
          color: "rgb(60, 64, 67)",
          marginBottom: "0.5rem",
        }}
      >
        에코노 캘린더
      </div>
      <SceduleType>
        <ScheduleToggle color={"#ff5b5b"} />
        공식행사
      </SceduleType>
      <SceduleType>
        <ScheduleToggle color={"#63ABFF"} />
        주간발표
      </SceduleType>
    </PublicFilterFrame>
  );
};

export default PublicFilter;

const PublicFilterFrame = styled.div`
  margin: 1.3rem;
`;

const SceduleType = styled.div`
  display: flex;
  align-items: center;
`;
