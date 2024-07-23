import { AiOutlineClose } from "react-icons/ai";
import styled from "styled-components";

const FilterDelete = () => {
  return (
    <>
      <StyledDetailIcon
        className="detail-icon"
        onMouseEnter={(e) => (e.currentTarget.style.color = "#000")}
        onMouseLeave={(e) => (e.currentTarget.style.color = "#B8B6B6")}
      >
        <AiOutlineClose size="1.1rem" />
      </StyledDetailIcon>
    </>
  );
};

export default FilterDelete;

const StyledDetailIcon = styled.button`
  background-color: #fff;
  border: none;
  display: none;
  margin-left: 0.5rem;
`;
