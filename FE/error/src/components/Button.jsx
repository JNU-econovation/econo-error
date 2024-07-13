import styled from "styled-components";
import { IoChevronDown } from "react-icons/io5";

const Button = ({ content, onClick, isActive }) => {
  return (
    <DefaultButton onClick={onClick} isActive={isActive}>
      {content}
      <IoChevronDown style={{ marginLeft: "5px" }} />
    </DefaultButton>
  );
};

export default Button;

const DefaultButton = styled.button`
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0.5rem 0.8rem;
  background-color: ${(props) => (props.isActive ? "#ffffff" : "#e7e7e7")};
  color: ${(props) => (props.isActive ? "#495057" : "#323232")};
  border: 1px solid #ced4da;
  border-radius: 3px;
  font-weight: 500;
  margin-right: 0.3rem;
  cursor: pointer;
  transition: all 0.3s ease;

  &:hover {
    background-color: ${(props) => (props.isActive ? "#f8f9fa" : "#d4d4d4")};
  }
`;
