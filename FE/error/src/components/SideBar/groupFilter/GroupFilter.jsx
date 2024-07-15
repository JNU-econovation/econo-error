import { useState } from "react";
import { FaPlus } from "react-icons/fa6";
import { SlArrowDown } from "react-icons/sl";
import styled from "styled-components";
import FilterCreateModal from "../../../utils/filterUtils/FilterCreateModal";
import FilterList from "../../../utils/filterUtils/FilterList";

const GroupFilter = ({ filterLists, addNewFilter }) => {
  const [groupFilterIsOpen, setGroupFilterIsOpen] = useState(false);
  const [filterListsIsOpen, setFilterListsIsOpen] = useState(true);
  const createGroupFilter = () => {
    setGroupFilterIsOpen(true);
  };
  const handleArrowDown = () => {
    setFilterListsIsOpen(!filterListsIsOpen);
  };
  return (
    <>
      <StyledGroupFilterFrame>
        <StyledTextContainer>
          <span
            style={{
              color: "#333333",
              marginBottom: "0.5rem",
              fontSize: "1.1rem",
            }}
          >
            그룹 캘린더
          </span>
        </StyledTextContainer>
        <StyledDetailIcon>
          <StyledGroupFilterPlusBtn onClick={createGroupFilter}>
            <FaPlus />
          </StyledGroupFilterPlusBtn>
          <StyledIndividualFilterArrowDownBtn onClick={handleArrowDown}>
            <SlArrowDown style={{ fontWeight: "bold", marginLeft: "0.5rem" }} />
          </StyledIndividualFilterArrowDownBtn>
          <FilterCreateModal
            isOpen={groupFilterIsOpen}
            onRequestClose={() => setGroupFilterIsOpen(false)}
            filterModalType={"group"}
            addNewFilter={addNewFilter}
          />
        </StyledDetailIcon>
      </StyledGroupFilterFrame>
      {filterListsIsOpen && <FilterList filterLists={filterLists} />}
    </>
  );
};

export default GroupFilter;

const StyledGroupFilterFrame = styled.div`
  margin: 1.3rem;
  margin-bottom: 0.9rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
`;
const StyledTextContainer = styled.div``;
const StyledDetailIcon = styled.div``;

const StyledGroupFilterPlusBtn = styled.button`
  background: none;
  border: none;
`;
const StyledIndividualFilterArrowDownBtn = styled.button`
  background: none;
  border: none;
`;
