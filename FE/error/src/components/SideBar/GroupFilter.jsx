import { FaPlus } from "react-icons/fa6";
import { SlArrowDown } from "react-icons/sl";
import styled from "styled-components";

const GroupFilter = () => {
  const createGroupFilter = () => {};
  return (
    <GroupFilterFrame>
      <TextContainer>
        <span style={{ color: "rgb(60, 64, 67)", marginBottom: "0.5rem" }}>
          그룹 캘린더
        </span>
      </TextContainer>
      <IconContainer>
        <GroupFilterPlusBtn onClick={createGroupFilter}>
          <FaPlus />
          <SlArrowDown style={{ fontWeight: "bold", marginLeft: "0.5rem" }} />
        </GroupFilterPlusBtn>
      </IconContainer>
    </GroupFilterFrame>
  );
};

export default GroupFilter;

const GroupFilterFrame = styled.div`
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
const GroupFilterPlusBtn = styled.button`
  background: none;
  border: none;
`;
