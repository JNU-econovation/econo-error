import React, { useState, useEffect } from "react";
import Modal from "react-modal";
import "./CreateModal.css";

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
      <input placeholder="제목" />
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
      <input type="time" />
      <input type="time" />
      <p>모달 내용</p>
      <button onClick={onRequestClose}>저장</button>
    </Modal>
  );
};

export default CreateModal;
