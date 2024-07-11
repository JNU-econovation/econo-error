import { useState } from "react";
import { IoClose } from "react-icons/io5";
import Modal from "react-modal";
import styled from "styled-components";
import "./CreateFilterModal.css";
import GroupFilterCreateModal from "../groupFilter/GroupFilterCreateModal";
import FilterColorSelect from "./FilterColorSelect";

const FilterCreateModal = ({ isOpen, onRequestClose, filterModalType }) => {
  const [eventName, setEventName] = useState("");

  const handleTitleChange = (event) => {
    setEventName(event.target.value);
  };

  return (
    <>
      <Modal
        isOpen={isOpen}
        onRequestClose={() => {
          onRequestClose();
        }}
        className="CreatePostCss"
        overlayClassName="overlay"
      >
        <StyledModalBar>
          <div>새 필터 만들기</div>
          <button onClick={onRequestClose}>
            <IoClose size="1.2rem" />
          </button>
        </StyledModalBar>
        <StyledDetail>
          <TitleInput
            placeholder="제목"
            value={eventName}
            onChange={handleTitleChange}
          />
          <FilterColorSelect />
          {filterModalType === "individual" ? "" : <GroupFilterCreateModal />}
        </StyledDetail>
        <StyledModalFooter>
          <StyledCreateFilterBtn>
            <span>필터 만들기</span>
          </StyledCreateFilterBtn>
        </StyledModalFooter>
      </Modal>
    </>
  );
};

export default FilterCreateModal;

const StyledModalBar = styled.div`
  margin: 2rem;
  display: flex;
  justify-content: space-between;
  outline: none;

  button {
    width: 3rem;
    background-color: #fff;
    border: none;
    svg {
      size: 5rem;
    }
  }
`;

const StyledDetail = styled.div`
  margin: 2rem;
`;
const TitleInput = styled.input`
  width: 95%;
  height: 2rem;
  margin-bottom: 2rem;
  font-size: 1.5rem;
  border: none;
  border-bottom: 1px solid #495057;
  outline: none;
`;

const StyledModalFooter = styled.div`
  margin: 2rem;
  display: flex;
  flex-direction: row-reverse;
`;
const StyledCreateFilterBtn = styled.button`
  width: 6rem;
  height: 2rem;
  background-color: #ff9999;
  border-radius: 0.5rem;
  border: none;
  span {
    font-size: 0.88rem;
    font-weight: bold;
    color: #fff;
  }
`;
