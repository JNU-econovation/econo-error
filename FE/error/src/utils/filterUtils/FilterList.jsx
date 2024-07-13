import styled from "styled-components";
import ScheduleToggle from "./ScheduleToggle";
const FilterList = ({ filterLists }) => {
  return (
    <StyledFilterListFrame>
      {filterLists.map((filterList, index) => (
        <StyledSceduleType key={index}>
          <ScheduleToggle color={filterList.color} />
          {filterList.title}
        </StyledSceduleType>
      ))}
    </StyledFilterListFrame>
  );
};

export default FilterList;

const StyledFilterListFrame = styled.div`
  display: flex;
  flex-direction: column;
  margin-left: 1.3rem;
  margin-bottom: 0.3rem;
`;
const StyledSceduleType = styled.div`
  display: flex;
  align-items: center;
  margin-bottom: 0.3rem;
`;
