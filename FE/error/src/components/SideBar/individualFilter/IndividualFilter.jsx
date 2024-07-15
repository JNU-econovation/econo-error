import { useState } from "react";
import { FaPlus } from "react-icons/fa6";
import { SlArrowDown } from "react-icons/sl";
import styled from "styled-components";
import FilterCreateModal from "../../../utils/filterUtils/FilterCreateModal";
import FilterList from "../../../utils/filterUtils/FilterList";

const IndividualFilter = ({ filterLists, addNewFilter }) => {
  const [individualFilterIsOpen, setindividualFilterIsOpen] = useState(false);
  const [filterListsIsOpen, setFilterListsIsOpen] = useState(true);
  const createIndividualFilter = () => {
    setindividualFilterIsOpen(true);
  };

  const handleArrowDown = () => {
    setFilterListsIsOpen(!filterListsIsOpen);
  };
  return (
    <>
      <StyledIndividualFilterFrame>
        <StyledTextContainer>
          <span
            style={{
              color: "#333333",
              fontSize: "1.1rem",
            }}
          >
            개인 캘린더
          </span>
        </StyledTextContainer>
        <StyledDetailIcon>
          <StyledIndividualFilterPlusBtn onClick={createIndividualFilter}>
            <FaPlus />
          </StyledIndividualFilterPlusBtn>
          <StyledIndividualFilterArrowDownBtn onClick={handleArrowDown}>
            <SlArrowDown style={{ fontWeight: "bold", marginLeft: "0.5rem" }} />
          </StyledIndividualFilterArrowDownBtn>
          <FilterCreateModal
            isOpen={individualFilterIsOpen}
            onRequestClose={() => setindividualFilterIsOpen(false)}
            filterModalType={"individual"}
            addNewFilter={addNewFilter}
          />
        </StyledDetailIcon>
      </StyledIndividualFilterFrame>
      {filterListsIsOpen && <FilterList filterLists={filterLists} />}
    </>
  );
};

export default IndividualFilter;

const StyledIndividualFilterFrame = styled.div`
  margin: 1.3rem;
  margin-bottom: 0.9rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
`;
const StyledTextContainer = styled.div`
  /* 필요한 스타일이 있다면 여기에 추가 */
`;
const StyledDetailIcon = styled.div``;
const StyledIndividualFilterPlusBtn = styled.button`
  background: none;
  border: none;
`;

const StyledIndividualFilterArrowDownBtn = styled.button`
  background: none;
  border: none;
`;
