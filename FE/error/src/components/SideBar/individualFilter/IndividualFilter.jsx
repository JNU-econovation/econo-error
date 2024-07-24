import { useState } from "react";
import { AiOutlinePlus } from "react-icons/ai";
import { IoIosArrowDown } from "react-icons/io";
import { IoIosArrowUp } from "react-icons/io";
import styled from "styled-components";
import FilterCreateModal from "../../../utils/filterUtils/FilterCreateModal";
import FilterList from "../../../utils/filterUtils/FilterList";

const IndividualFilter = ({
  filterLists,
  addNewFilter,
  updateDeleteFilter,
  onFilterChange,
}) => {
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
            <AiOutlinePlus style={{ fontSize: "1.3rem" }} />
          </StyledIndividualFilterPlusBtn>
          <StyledIndividualFilterArrowDownBtn onClick={handleArrowDown}>
            {filterListsIsOpen ? (
              <IoIosArrowDown
                style={{
                  fontWeight: "bold",
                  marginLeft: "0.5rem",
                  fontSize: "1.3rem",
                }}
              />
            ) : (
              <IoIosArrowUp
                style={{
                  fontWeight: "bold",
                  marginLeft: "0.5rem",
                  fontSize: "1.3rem",
                }}
              />
            )}
          </StyledIndividualFilterArrowDownBtn>
          <FilterCreateModal
            isOpen={individualFilterIsOpen}
            onRequestClose={() => setindividualFilterIsOpen(false)}
            filterModalType={"individual"}
            addNewFilter={addNewFilter}
          />
        </StyledDetailIcon>
      </StyledIndividualFilterFrame>
      {filterListsIsOpen && (
        <FilterList
          filterLists={filterLists}
          updateDeleteFilter={updateDeleteFilter}
        />
      )}
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
