import React from "react";
import Modal from "react-modal";
import "./CreateModal.css";

const CreateModal = ({ isOpen, onRequestClose }) => {
  return (
    <Modal
      isOpen={isOpen}
      onRequestClose={onRequestClose}
      className="modal"
      overlayClassName="overlay"
    >
      <input placeholder="제목" />

      <input type="date" id="date" />
      <input type="date" id="date" />
      <input type="time" />
      <input type="time" />
      <p>모달 내용</p>
      <button onClick={onRequestClose}>저장</button>
    </Modal>
  );
};

export default CreateModal;
