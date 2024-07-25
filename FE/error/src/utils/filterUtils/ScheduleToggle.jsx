import { useState } from "react";
import styled from "styled-components";

const ScheduleToggle = ({ color, id, onToggle }) => {
  const [clicked, setClicked] = useState(true);
  const handleOnClick = () => {
    const newClickedState = !clicked;
    setClicked(newClickedState);

    onToggle(id, newClickedState);
  };

  return (
    <>
      <StyledClickedBox
        type="checkbox"
        onClick={handleOnClick}
        color={color}
        defaultChecked={clicked}
      />
    </>
  );
};

export default ScheduleToggle;

const StyledClickedBox = styled.input`
  appearance: none;
  width: 1.15rem;
  height: 1.15rem;
  border: 1.5px solid ${(props) => props.color};
  opacity: 0.7;
  border-radius: 0.2rem;
  &:checked {
    background-image: url("data:image/svg+xml,%3Csvg viewBox='-2 -2 25 25' xmlns='http://www.w3.org/2000/svg'%3E%3Cpath d='M18 7.2C18 7.2 8.55 16.65 8.1 17.1C7.65 17.55 3.15 12.15 2.7 11.7C2.25 11.25 4.5 9.9 4.5 9.9L8.1 13.5L16.2 5.4C16.2 5.4 17.55 6.3 18 7.2Z' fill='white' stroke='white' stroke-width='0.63' stroke-linejoin='round' stroke-linecap='round'/%3E%3C/svg%3E");
    background-color: ${(props) => props.color};
    opacity: 0.7;
  }
  margin-right: 0.5rem;
`;
