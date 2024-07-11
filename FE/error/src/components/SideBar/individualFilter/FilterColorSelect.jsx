import styled from "styled-components";
const FilterColorSelect = () => {
  function getColorByIndex(index) {
    const colors = [
      "#ABDEE6",
      "#F3B0C3",
      "#C6DBDA",
      "#CBAACB",
      "#FED7C3",
      "#789BDE",
      "#55CBCD",
      "#FF968A",
      "#FF968A",
      "#7367F0",
      "#FFC8A2",
      "#AAEF98",
    ];

    return colors[index % colors.length];
  }
  return (
    <div>
      <div>색상</div>

      <StyledFilterColorFrame>
        {Array.from({ length: 12 }).map((_, index) => (
          <StyledFilterColor
            key={index}
            backgroundColor={getColorByIndex(index)}
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
`;

const StyledFilterColorFrame = styled.div`
  margin-top: 1rem;
  margin-bottom: 2rem;
  width: 15rem;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
`;
