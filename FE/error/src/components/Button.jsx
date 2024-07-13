import styled from "styled-components";

const Button = ({ content }) => {
  return (
    <div>
      <DefaultButton>{content}</DefaultButton>
    </div>
  );
};

export default Button;

const DefaultButton = styled.button`
  padding: 0.5rem 0.8rem;
  background-color: #f8f9fa;
  border: 1px solid #ced4da;
  border-radius: 3px;
  font-weight: 500;
  margin-right: 0.5rem;
  color: #495057;
  cursor: pointer;
  &:hover {
    background-color: #e9ecef;
  }
`;
