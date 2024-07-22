import { AiOutlineClose, AiOutlineMore } from "react-icons/ai";
import styled from "styled-components";
import ScheduleToggle from "./ScheduleToggle";

const FilterList = ({ filterLists }) => {
  const filterDelete = () => {};

  return (
    <StyledFilterListFrame>
      {filterLists.map((filterList, index) => (
        <StyledSceduleType key={index}>
          <StyledTitle>
            <ScheduleToggle color={filterList.filterColor} />
            {filterList.filterName}
          </StyledTitle>
          <StyledDetail>
            <StyledDetailIcon
              onClick={filterDelete}
              className="detail-icon"
              onMouseEnter={(e) => (e.currentTarget.style.color = "#000")}
              onMouseLeave={(e) => (e.currentTarget.style.color = "#B8B6B6")}
            >
              <AiOutlineClose size="1.1rem" />
            </StyledDetailIcon>
            <StyledDetailIcon
              className="detail-icon"
              onMouseEnter={(e) => (e.currentTarget.style.color = "#000")}
              onMouseLeave={(e) => (e.currentTarget.style.color = "#B8B6B6")}
            >
              <AiOutlineMore size="1.4rem" />
            </StyledDetailIcon>
          </StyledDetail>
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
const StyledDetailIcon = styled.button`
  background-color: #fff;
  border: none;
  display: none;
  margin-left: 0.5rem;
`;
