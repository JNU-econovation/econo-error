import { useState } from "react";
import { IoClose } from "react-icons/io5";
import styled from "styled-components";
import Modal from "react-modal";
import "./CreateFilterModal.css";
import GroupFilterCreateModal from "../../components/SideBar/groupFilter/GroupFilterCreateModal";
import FilterColorSelect from "./FilterColorSelect";
import axios from "axios";

const FilterCreateModal = ({
  isOpen,
  onRequestClose,
  filterModalType,
  addNewFilter,
}) => {
  const [filterTitle, setFilterTitle] = useState("");
  const [filterColor, setFilterColor] = useState("");

  const handleTitleChange = (event) => {
    setFilterTitle(event.target.value);
  };
  const handleCreateFilter = () => {
    const newFilter = {
      filterName: filterTitle,
      filterColor: filterColor,
    };
    //axios.post("/api/filter", newFilter).then((res) => {
    addNewFilter(newFilter);
    setFilterTitle("");
    setFilterColor("");
    onRequestClose();
    //});
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
          <StyledTitleInput
            placeholder="제목"
            value={filterTitle}
            onChange={handleTitleChange}
          />
          <FilterColorSelect setFilterColor={setFilterColor} />
          {filterModalType === "individual" ? "" : <GroupFilterCreateModal />}
        </StyledDetail>
        <StyledModalFooter>
          <StyledCreateFilterBtn onClick={handleCreateFilter}>
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
const StyledTitleInput = styled.input`
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
    color: #fff;
  }
`;
