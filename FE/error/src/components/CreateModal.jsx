import React, { useState, useEffect } from "react";
import Modal from "react-modal";
import "./CreateModal.css";
import styled from "styled-components";
import TimeSelect from "./TimeSelect";

const CreateModal = ({ isOpen, onRequestClose, selectedDate }) => {
  const [title, setTitle] = useState(""); // 제목 상태 추가
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");

  useEffect(() => {
    if (isOpen && selectedDate) {
      setStartDate(selectedDate);
      setEndDate(selectedDate);
    }
  }, [isOpen, selectedDate]);

  const handleTitleChange = (event) => {
    setTitle(event.target.value);
  };

  const handleStartDateChange = (event) => {
    setStartDate(event.target.value);
  };

  const handleEndDateChange = (event) => {
    setEndDate(event.target.value);
  };

  // 백엔드에 데이터를 전송하는 함수
  const saveData = () => {
    // 백엔드 엔드포인트 URL, 여기서는 예시로 작성
    const url = `${import.meta.env.VITE_ERROR_API}/api/calendar`;
    const data = {
      title,
      startDate,
      endDate,
    };

    fetch(url, {
      method: "POST", // HTTP 메소드
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data), // JSON 문자열로 변환
    })
      .then((response) => response.json())
      .then((data) => {
        console.log("Success:", data);
        onRequestClose(); // 모달 닫기
      })
      .catch((error) => {
        console.error("Error:", error);
      });
  };

  return (
    <Modal
      isOpen={isOpen}
      onRequestClose={onRequestClose}
      className="modal"
      overlayClassName="overlay"
    >
      <TitleInput
        placeholder="제목"
        value={title}
        onChange={handleTitleChange} // 제목 입력 시 상태 업데이트
      />
      <div style={{ display: "flex" }}>
        <input type="date" value={startDate} onChange={handleStartDateChange} />
        <input type="date" value={endDate} onChange={handleEndDateChange} />
      </div>
      <div style={{ display: "flex" }}>
        <TimeSelect />
        <TimeSelect />
      </div>
      <div style={{ display: "flex", justifyContent: "flex-end" }}>
        <SaveButton onClick={saveData}>저장</SaveButton> {/* 저장 함수 호출 */}
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
