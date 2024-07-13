import { useState } from "react";
import { FaPlus } from "react-icons/fa6";
import { SlArrowDown } from "react-icons/sl";
import styled from "styled-components";
import FilterCreateModal from "../individualFilter/FilterCreateModal";

const GroupFilter = () => {
  const [groupFilterIsOpen, setGroupFilterIsOpen] = useState(false);

  const createGroupFilter = () => {
    setGroupFilterIsOpen(true);
  };

  return (
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
      <StyledGroupFilterPlusBtn onClick={createGroupFilter}>
        <FaPlus />
        <SlArrowDown style={{ fontWeight: "bold", marginLeft: "0.5rem" }} />
      </StyledGroupFilterPlusBtn>
      <FilterCreateModal
        isOpen={groupFilterIsOpen}
        onRequestClose={() => setGroupFilterIsOpen(false)}
        filterModalType={"group"}
      />
    </StyledGroupFilterFrame>
  );
};

export default GroupFilter;

const StyledGroupFilterFrame = styled.div`
  margin: 1.3rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
`;
const StyledTextContainer = styled.div``;

const StyledGroupFilterPlusBtn = styled.button`
  background: none;
  border: none;
`;
