import React, { useState } from "react"; // useState 추가
import Modal from "react-modal";
import "./CreateModal.css";
import { DayPicker } from "react-day-picker";
import "react-day-picker/dist/style.css";
import { format } from "date-fns"; // format 함수 추가

const CreateModal = ({ isOpen, onRequestClose }) => {
  return (
    <Modal
      isOpen={isOpen}
      onRequestClose={onRequestClose}
      className="modal"
      overlayClassName="overlay"
    >
      <input placeholder="제목" />
      <input type="date" id="date1" /> {/* id 수정 */}
      <input type="date" id="date2" /> {/* id 수정 */}
      <input type="time" id="time1" /> {/* id 추가 */}
      <input type="time" id="time2" /> {/* id 추가 */}
      <p>모달 내용</p>
      <button onClick={onRequestClose}>저장</button>
    </Modal>
  );
};

export default CreateModal;
