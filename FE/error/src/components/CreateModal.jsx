import { useState, useEffect } from "react";
import Modal from "react-modal";
import "./CreateModal.css";
import styled from "styled-components";
import TimeSelect from "./TimeSelect";
import ReactQuill from "react-quill";

const CreateModal = ({ isOpen, onRequestClose, selectedDate }) => {
  const [eventName, setEventName] = useState("");
  const [StartDate, setStartDate] = useState("");
  const [EndDate, setEndDate] = useState("");
  const [eventInfo, setEventInfo] = useState("");
  const [eventPlace, setEventPlace] = useState("");
  const [eventMemo, setEventMemo] = useState("");
  const [eventStartDate, setNewStartDate] = useState("");
  const [eventEndDate, setNewEndDate] = useState("");
  const [eventStartTime, setEventStartTime] = useState("00:00");
  const [eventEndTime, setEventEndTime] = useState("00:00");

  useEffect(() => {
    if (isOpen && selectedDate) {
      setEventName("");
      setStartDate(selectedDate);
      setEndDate(selectedDate);
      setEventInfo("");
      setEventPlace("");
      setEventMemo("");
      setNewStartDate(selectedDate + "T" + eventStartTime);
      setNewEndDate(selectedDate + "T" + eventEndTime);
    }
  }, [isOpen, selectedDate]);

  const handleTitleChange = (event) => {
    setEventName(event.target.value);
  };

  const handleStartDateChange = (event) => {
    setStartDate(event.target.value);

    // 새로운 시작 날짜 설정. 기존에 선택했던 시작 시간을 포함시킨다.
    const newStartDate = `${event.target.value}T${eventStartTime}`;
    setNewStartDate(newStartDate);
  };

  const handleEndDateChange = (event) => {
    setEndDate(event.target.value);

    // 종료 날짜가 변경될 때 종료 날짜와 기존에 설정된 종료 시간을 결합하여 설정
    const newEndDate = `${event.target.value}T${eventEndTime}`;
    setNewEndDate(newEndDate);
  };

  const handleStartTimeSelect = (time) => {
    // 시작 시간 선택시, 날짜와 시간을 결합하여 시작 날짜와 시간 설정
    const startDate = `${StartDate}T${time}`;
    setEventStartTime(time);
    setNewStartDate(startDate);
  };

  const handleEndTimeSelect = (time) => {
    // 종료 시간 선택시, 날짜를 다음 날로 설정하고 시간을 결합하여 종료 날짜와 시간 설정
    let endDate = new Date(EndDate);
    endDate.setDate(endDate.getDate() + 1);
    const newEndDate = `${endDate.toISOString().split("T")[0]}T${time}`;
    setEventEndTime(time);
    setNewEndDate(newEndDate);

    //  const endDate = `${EndDate}T${time}`;
    //setEventEntTime(time);
    //setNewEndDate(endDate);
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
        //window.location.reload();
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
        placeholder="제목을 입력하세요"
        value={eventName}
        onChange={handleTitleChange}
      />
      <div style={{ display: "flex" }}>
        <input type="date" value={StartDate} onChange={handleStartDateChange} />
        <input
          type="date"
          value={EndDate}
          onChange={handleEndDateChange}
          min={StartDate}
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
        <SaveButton onClick={saveData} disabled={!eventName}>
          저장
        </SaveButton>
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
  outline: none;
  cursor: pointer;
  right: 0;
  background-color: ${(props) => (props.disabled ? "#e0e0e0" : "white")};
  color: ${(props) => (props.disabled ? "#9e9e9e" : "#3e3e3e")};
  border: ${(props) => (props.disabled ? "none" : "0.5px solid #858585")};

  &:disabled {
    cursor: default;
  }
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
