import styled from "styled-components";
import ScheduleToggle from "../ScheduleToggle";

const PublicFilter = () => {
  return (
    <PublicFilterFrame>
      <div
        style={{
          color: "#333333",
          marginBottom: "0.5rem",
        }}
      >
        <div style={{ fontSize: "1.1rem" }}>에코노 캘린더</div>
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
  margin-bottom: 0.3rem;
`;
