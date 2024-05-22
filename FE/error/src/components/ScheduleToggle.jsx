import styled from "styled-components";
import { useState } from "react";

const ScheduleToggle = ({ color }) => {
  const [clicked, setClicked] = useState(true);

  const handleOnClick = () => {
    setClicked(!clicked);
    console.log(clicked);
  };

  return (
    <>
      <ClickedBox type="checkbox" onClick={handleOnClick} color={color} />
    </>
  );
};

export default ScheduleToggle;

const ClickedBox = styled.input`
  appearance: none;
  width: 1.4rem;
  height: 1.4rem;
  border: 1.5px solid gainsboro;
  opacity: 0.7;
  border-radius: 0.35rem;
  &:checked {
    background-image: url("data:image/svg+xml,%3csvg viewBox='0 0 16 16' fill='white' xmlns='http://www.w3.org/2000/svg'%3e%3cpath d='M5.707 7.293a1 1 0 0 0-1.414 1.414l2 2a1 1 0 0 0 1.414 0l4-4a1 1 0 0 0-1.414-1.414L7 8.586 5.707 7.293z'/%3e%3c/svg%3e");
    background-color: ${(props) => props.color};
    opacity: 0.7;
  }
`;
