import { useState } from "react";
import { FaPlus } from "react-icons/fa6";
import { SlArrowDown } from "react-icons/sl";
import styled from "styled-components";
import FilterCreateModal from "./FilterCreateModal";

const IndividualFilter = () => {
  const [individualFilterIsOpen, setindividualFilterIsOpen] = useState(false);

  const createIndividualFilter = () => {
    setindividualFilterIsOpen(true);
  };

  return (
    <IndividualFilterFrame>
      <TextContainer>
        <span
          style={{
            color: "#333333",
            marginBottom: "0.5rem",
            fontSize: "1.1rem",
          }}
        >
          개인 캘린더
        </span>
      </TextContainer>
      <IconContainer>
        <IndividualFilterPlusBtn onClick={createIndividualFilter}>
          <FaPlus />
          <SlArrowDown style={{ fontWeight: "bold", marginLeft: "0.5rem" }} />
        </IndividualFilterPlusBtn>
        <FilterCreateModal
          isOpen={individualFilterIsOpen}
          onRequestClose={() => setindividualFilterIsOpen(false)}
          filterModalType={"individual"}
        />
      </IconContainer>
    </IndividualFilterFrame>
  );
};

export default IndividualFilter;

const IndividualFilterFrame = styled.div`
  margin: 1.3rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
`;
const TextContainer = styled.div`
  /* 필요한 스타일이 있다면 여기에 추가 */
`;

const IconContainer = styled.div`
  /* 필요한 스타일이 있다면 여기에 추가 */
`;
const IndividualFilterPlusBtn = styled.button`
  background: none;
  border: none;
`;
