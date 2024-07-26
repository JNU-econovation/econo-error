import styled from "styled-components";
import ScheduleToggle from "./ScheduleToggle";
import FilterModify from "./FilterModify";
import FilterDelete from "./FilterDelete";

const FilterList = ({ filterLists, updateDeleteFilter }) => {
  return (
    <StyledFilterListFrame>
      {filterLists.map((filterList, index) => (
        <StyledScheduleType key={index}>
          <StyledTitle>
            <ScheduleToggle color={filterList.filterColor} />
            {filterList.filterName}
          </StyledTitle>
          <StyledDetail>
            <FilterDelete
              filterID={filterList.filterId}
              updateDeleteFilter={updateDeleteFilter}
            />
            <FilterModify
              filterName={filterList.filterName}
              filterID={filterList.filterId}
            />
          </StyledDetail>
        </StyledScheduleType>
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
const StyledScheduleType = styled.div`
  height: 1.8rem;
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 0.3rem;
  &:hover .detail-icon {
    display: block;
  }
`;
const StyledDetail = styled.div`
  display: flex;
  align-items: center;
  margin-right: 1.3rem;
`;
const StyledTitle = styled.div`
  display: flex;
  align-items: center;
`;
