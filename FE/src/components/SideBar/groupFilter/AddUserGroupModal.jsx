import Modal from "react-modal";
import styled from "styled-components";
import "../individualFilter/CreateFilterModal.css";

const AddUserGroupModal = ({ isOpen, onRequestClose }) => {
  return (
    <div>
      <Modal
        isOpen={isOpen}
        onRequestClose={onRequestClose}
        className="CreatePostCss"
        overlayClassName="overlay"
      >
        <StyledAddUserGroupFrame></StyledAddUserGroupFrame>
        <StyldedAddUserGroupFooter>
          <StyledCancelAddBtn onClick={onRequestClose}>
            <div>취소</div>
          </StyledCancelAddBtn>
          <StyledCancelAddBtn>
            <div>추가</div>
          </StyledCancelAddBtn>
        </StyldedAddUserGroupFooter>
      </Modal>
    </div>
  );
};

export default AddUserGroupModal;

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
