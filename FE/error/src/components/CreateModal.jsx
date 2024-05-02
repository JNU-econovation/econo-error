import { useState, useEffect } from "react";
import Modal from "react-modal";
import "./CreateModal.css";
import styled from "styled-components";
import TimeSelect from "./TimeSelect";
import ReactQuill from "react-quill";

const CreateModal = ({ isOpen, onRequestClose, selectedDate }) => {
  const [eventName, setEventName] = useState("");
  const [eventStartDate, setEventStartDate] = useState("");
  const [eventEndDate, setEventEndDate] = useState("");
  const [eventInfo, setEventInfo] = useState("");
  const [eventPlace, setEventPlace] = useState("");
  const [eventMemo, setEventMemo] = useState("");

  useEffect(() => {
    if (isOpen && selectedDate) {
      setEventName("");
      setEventStartDate(selectedDate);
      setEventEndDate(selectedDate);
      setEventInfo("");
      setEventPlace("");
      setEventMemo("");
    }
  }, [isOpen, selectedDate]);

  const handleTitleChange = (event) => {
    setEventName(event.target.value);
  };

  const handleStartDateChange = (event) => {
    setEventStartDate(event.target.value);
  };

  const handleEndDateChange = (event) => {
    setEventEndDate(event.target.value);
  };

  const handleStartTimeSelect = (time) => {
    setEventStartDate((prev) => `${prev.split("T")[0]}T${time}`);
  };

  const handleEndTimeSelect = (time) => {
    setEventEndDate((prev) => `${prev.split("T")[0]}T${time}`);
  };

  const handleMemoChange = (e) => {
    setEventMemo(e.replace(/<[^>]*>/g, ""));
  };

  const handlePlaceChange = (e) => {
    setEventPlace(e.target.value);
    console.log(e.target.value);
  };

  const saveData = () => {
    const url = `${import.meta.env.VITE_ERROR_API}/api/calendar`;
    const data = {
      eventName,
      eventStartDate,
      eventEndDate,
      eventInfo: eventMemo,
      eventPlace,
    };
    console.log(JSON.stringify(data));
    fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
      credentials: "include",
    })
      .then((response) => response.json())
      .then((data) => {
        console.log("Success:", data);
        onRequestClose();
        window.location.reload();
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
        value={eventName}
        onChange={handleTitleChange}
      />
      <div style={{ display: "flex" }}>
        <input
          type="date"
          value={eventStartDate}
          onChange={handleStartDateChange}
        />
        <input
          type="date"
          value={eventEndDate}
          onChange={handleEndDateChange}
        />
      </div>
      <div style={{ display: "flex" }}>
        <TimeSelect onTimeSelect={handleStartTimeSelect} />
        <TimeSelect onTimeSelect={handleEndTimeSelect} />
      </div>
      <PlaceSelect
        placeholder="위치 추가"
        onChange={handlePlaceChange}
      ></PlaceSelect>
      <EditorBox>
        <ReactQuill placeholder={"설명 추가"} onChange={handleMemoChange} />
      </EditorBox>
      <div style={{ display: "flex", justifyContent: "flex-end" }}>
        <SaveButton onClick={saveData}>저장</SaveButton>
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

const EditorBox = styled.div`
  .ql-editor {
    height: 110px;
    overflow-y: auto;
  }
  .ql-editor::before {
    font-style: normal !important;
    color: #999 !important;
  }
`;

const PlaceSelect = styled.input`
  border: none;
  width: 100%;
  outline: none;
`;
