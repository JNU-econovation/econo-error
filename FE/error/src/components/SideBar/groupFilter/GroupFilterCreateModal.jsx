import { useState } from "react";
import styled from "styled-components";
import Modal from "react-modal";
import "../../../utils/filterUtils/CreateFilterModal.css";

const GroupFilterCreateModal = () => {
  const [addUserGroupModalIsOpen, setAddUserGroupModalIsOpen] = useState(false);
  const handleGroupBtnClick = () => {
    setAddUserGroupModalIsOpen(true);
  };
  return (
    <>
      <div>특정 사용자와 공유</div>
      <StyledUserShareContainer></StyledUserShareContainer>
      <StyledAddUserGroupBtn onClick={handleGroupBtnClick}>
        <div>+ 사용자 및 그룹 추가</div>
      </StyledAddUserGroupBtn>
      <Modal
        isOpen={addUserGroupModalIsOpen}
        onRequestClose={() => {
          setAddUserGroupModalIsOpen(false);
        }}
        className="CreatePostCss"
        overlayClassName="overlay"
      >
        <StyledAddUserGroupFrame></StyledAddUserGroupFrame>
        <StyldedAddUserGroupFooter>
          <StyledCancelAddBtn>
            <div>취소</div>
          </StyledCancelAddBtn>
          <StyledCancelAddBtn>
            <div>추가</div>
          </StyledCancelAddBtn>
        </StyldedAddUserGroupFooter>
      </Modal>
    </>
  );
};

export default GroupFilterCreateModal;

const StyledUserShareContainer = styled.div`
  border-top: 1px solid #b8b8b8;
  border-bottom: 1px solid #b8b8b8;
  height: 4.875rem;
  width: 95%;
  margin-top: 1rem;
  margin-bottom: 1rem;
`;

const StyledAddUserGroupBtn = styled.button`
  width: 10rem;
  height: 2rem;
  background-color: #fff;
  border-radius: 0.5rem;
  border: 1px solid #bbbbbb;
  div {
    font-size: 0.88rem;
    font-weight: bold;
    color: #ff9999;
  }
`;

const StyledAddUserGroupFrame = styled.div`
  margin: 2.5625rem;
`;

const StyledCancelAddBtn = styled.button`
  width: 4rem;
  height: 2rem;
  background-color: #fff;
  border-radius: 0.5rem;
  border: none;
  div {
    font-size: 0.88rem;
    font-weight: bold;
    color: #ff9999;
  }
`;

const StyldedAddUserGroupFooter = styled.div`
  display: flex;
  justify-content: end;
`;
