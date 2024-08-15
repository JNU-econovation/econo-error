import { useState } from "react";
import styled from "styled-components";

const FilterColorSelect = ({ setFilterColor }) => {
  const [selectedColor, setSelectedColor] = useState(null);

  function getColorByIndex(index) {
    const colors = [
      "#ABDEE6",
      "#F3B0C3",
      "#C6DBDA",
      "#CBAACB",
      "#FED7C3",
      "#789BDE",
      "#DEAC80",
      "#D6EFD8",
      "#FF968A",
      "#D4BDAC",
      "#ffd4b5",
      "#AAEF98",
    ];

    return colors[index % colors.length];
  }

  const handleColorClick = (index) => {
    const color = getColorByIndex(index);
    setSelectedColor(color);
    setFilterColor(color);
  };

  return (
    <div>
      <div>색상</div>

      <StyledFilterColorFrame>
        {Array.from({ length: 12 }).map((_, index) => (
          <StyledFilterColor
            key={index}
            backgroundColor={getColorByIndex(index)}
            onClick={() => handleColorClick(index)}
            isSelected={selectedColor === getColorByIndex(index)}
          />
        ))}
      </StyledFilterColorFrame>
    </div>
  );
};

export default FilterColorSelect;

const StyledFilterColor = styled.div`
  width: 2rem;
  height: 2rem;
  margin: 0.2rem;
  border-radius: 99999px;
  background-color: ${(props) => props.backgroundColor};
  border: none;
  box-shadow: ${(props) =>
    props.isSelected ? "0 0 10px 5px rgba(0, 0, 0, 0.1)" : "none"};
  transition: box-shadow 0.3s ease-in-out;
`;

const StyledFilterColorFrame = styled.div`
  margin-top: 1rem;
  margin-bottom: 2rem;
  width: 15rem;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
`;
