import { AiOutlineMore } from "react-icons/ai";
import styled from "styled-components";
import Modal from "react-modal";
import { useState } from "react";
import FilterColorSelect from "./FilterColorSelect";
import axios from "axios";

const FilterModify = ({ filterName, filterID }) => {
  const [showModal, setShowModal] = useState(false);
  const [clickPosition, setClickPosition] = useState({ x: 0, y: 0 });
  const [filterTitle, setFilterTitle] = useState(filterName);
  const [filterColor, setFilterColor] = useState("");

  const handleModify = (e) => {
    const rect = e.currentTarget.getBoundingClientRect();
    setClickPosition({ x: rect.left, y: rect.top });
    setShowModal(true);
  };
  const storedToken = localStorage.getItem("slackToken");

  const handleTitleChange = (event) => {
    setFilterTitle(event.target.value);
  };

  const handleModifyFilter = () => {
    const modifyFilter = {
      filterName: filterTitle,
      filterColor: filterColor,
    };
    axios
      .post(
        "/api/calendar/filter/" + filterID,
        {
          headers: { Authorization: `Bearer ${storedToken}` },
        },
        modifyFilter
      )
      .then((res) => {
        setShowModal(false);
        window.location.reload();
      });
  };

  const customStyles = {
    content: {
      position: "absolute",
      top: "auto",
      left: `${clickPosition.x}px`,
      right: "auto",
      bottom: `calc(100% - ${clickPosition.y}px)`,
      transform: "translateY(-10px)",
      marginRight: "-50%",
      borderRadius: "0.5rem",
      width: "19rem",
      height: "auto",
      backgroundColor: "white",
      boxShadow: "0 0 10px 8px rgba(0, 0, 0, 0.1)",
    },
    overlay: {
      backgroundColor: "rgba(0, 0, 0, 0)",
    },
  };

  return (
    <>
      <StyledDetailIcon
        onClick={handleModify}
        className="detail-icon"
        onMouseEnter={(e) => (e.currentTarget.style.color = "#000")}
        onMouseLeave={(e) => (e.currentTarget.style.color = "#B8B6B6")}
      >
        <AiOutlineMore size="1.4rem" />
      </StyledDetailIcon>
      <Modal
        isOpen={showModal}
        onRequestClose={() => setShowModal(false)}
        style={customStyles}
        contentLabel="Modify Filter Modal"
        className="FilterModal"
        overlayClassName="overlay"
      >
        <StyledDetail>
          <StyledTitleInput
            placeholder="제목"
            value={filterTitle}
            onChange={handleTitleChange}
          />
          <FilterColorSelect setFilterColor={setFilterColor} />
        </StyledDetail>
        <StyledModalFooter>
          <StyledCreateFilterBtn onClick={handleModifyFilter}>
            <span>필터수정</span>
          </StyledCreateFilterBtn>
        </StyledModalFooter>
      </Modal>
    </>
  );
};

export default FilterModify;

const StyledDetailIcon = styled.button`
  background-color: #fff;
  border: none;
  display: none;
  margin-left: 0.5rem;
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
  margin-right: 1.5rem;
  margin-bottom: 1rem;
  display: flex;
  flex-direction: row-reverse;
`;

const StyledCreateFilterBtn = styled.button`
  width: 4.5rem;
  height: 2rem;
  background-color: #ff9999;
  border-radius: 0.5rem;
  border: none;

  span {
    font-size: 0.88rem;
    color: #fff;
  }
`;
