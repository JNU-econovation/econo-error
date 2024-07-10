import { useState } from "react";
import { IoClose } from "react-icons/io5";
import Modal from "react-modal";
import styled from "styled-components";
import "./CreateFilterModal.css";

const BasicCreateModal = ({ isOpen, onRequestClose }) => {
  const [eventName, setEventName] = useState("");
  const handleTitleChange = (event) => {
    setEventName(event.target.value);
  };
  return (
    <>
      <Modal
        isOpen={isOpen}
        onRequestClose={onRequestClose}
        className="CreatePostCss"
        overlayClassName="overlay"
      >
        <StyledModalBar>
          <h1>새 필터 만들기</h1>
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
          <div>색상</div>
          <StyledFilterColorFrame>
            {Array.from({ length: 12 }).map((_, index) => (
              <StyledFilterColor key={index} />
            ))}
          </StyledFilterColorFrame>
        </StyledDetail>
        <StyledModalFooter>
          <StyledCreateFilter>
            <span>필터 만들기</span>
          </StyledCreateFilter>
        </StyledModalFooter>
      </Modal>
    </>
  );
};

export default BasicCreateModal;

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
  width: 90%;
  height: 2rem;
  margin-bottom: 2rem;
  font-size: 1.5rem;
  border: none;
  border-bottom: 1px solid #495057;
  outline: none;
`;

const StyledFilterColor = styled.div`
  width: 2rem;
  height: 2rem;
  margin: 0.2rem;
  border-radius: 99999px;
  background-color: black;
`;

const StyledFilterColorFrame = styled.div`
  margin-top: 1rem;
  width: 15rem;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
`;

const StyledModalFooter = styled.div`
  margin: 2rem;
  display: flex;
  flex-direction: row-reverse;
`;
const StyledCreateFilter = styled.button`
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
