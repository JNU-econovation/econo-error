import styled from "styled-components";
import ScheduleToggle from "../../common/ScheduleToggle";

const PublicFilter = () => {
  return (
    <StyledPublicFilterFrame>
      <div
        style={{
          color: "#333333",
          marginBottom: "0.9rem",
        }}
      >
        <div style={{ fontSize: "1.1rem" }}>에코노 캘린더</div>
      </div>
      <StyledSceduleType>
        <ScheduleToggle color={"#FF968A"} />
        공식행사
      </StyledSceduleType>
      <StyledSceduleType>
        <ScheduleToggle color={"#ffd4b5"} />
        주간발표
      </StyledSceduleType>
    </StyledPublicFilterFrame>
  );
};

export default PublicFilter;

const StyledPublicFilterFrame = styled.div`
  margin: 1.3rem;
`;

const StyledSceduleType = styled.div`
  display: flex;
  align-items: center;
  margin-bottom: 0.3rem;
`;
