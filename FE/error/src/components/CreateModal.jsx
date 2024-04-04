import React, { useState, useEffect } from "react";
import Modal from "react-modal";
import "./CreateModal.css";
import styled from "styled-components";
import TimeSelect from "./TimeSelect";

const CreateModal = ({ isOpen, onRequestClose, selectedDate }) => {
  // startDate와 endDate 상태 초기화
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  console.log(selectedDate);
  useEffect(() => {
    // 모달이 열릴 때마다 selectedDate로 날짜를 초기화합니다.
    if (isOpen && selectedDate) {
      setStartDate(selectedDate);
      setEndDate(selectedDate);
    }
  }, [isOpen, selectedDate]);

  // 날짜 선택 시 상태를 업데이트하는 함수
  const handleStartDateChange = (event) => {
    setStartDate(event.target.value);
  };

  const handleEndDateChange = (event) => {
    setEndDate(event.target.value);
  };

  return (
    <Modal
      isOpen={isOpen}
      onRequestClose={onRequestClose}
      className="modal"
      overlayClassName="overlay"
    >
      <TitleInput placeholder="제목" />
      <div style={{ display: "flex" }}>
        <input
          type="date"
          value={startDate}
          onChange={handleStartDateChange} // 사용자가 날짜를 선택하면 startDate 상태 업데이트
        />

        <input
          type="date"
          value={endDate}
          onChange={handleEndDateChange} // 사용자가 날짜를 선택하면 endDate 상태 업데이트
        />
      </div>
      <div style={{ display: "flex" }}>
        <TimeSelect />
        <TimeSelect />
      </div>
      <div style={{ display: "flex", justifyContent: "flex-end" }}>
        <SaveButton onClick={onRequestClose}>저장</SaveButton>
      </div>
    </Modal>
  );
};

export default CreateModal;

const TitleInput = styled.input`
  width: 100%;
  height: 2rem;
  margin-bottom: 2rem;
  font-size: 1.5rem;
  border: none;
  border-bottom: 1px solid #495057;
  outline: none;
`;

const SaveButton = styled.button`
  width: 4rem;
  height: 2rem;
  border-radius: 0.25rem;
  margin-top: 3rem;
  border: 0.5px solid #858585;
  color: #3e3e3e;
  outline: none;
  cursor: pointer;
  right: 0;
`;
