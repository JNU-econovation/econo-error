import { useState, useEffect } from "react";
import Modal from "react-modal";
import "./CreateModal.css";
import styled from "styled-components";
import TimeSelect from "./TimeSelect";
import ReactQuill from "react-quill";
import { format, addDays, compareAsc, parseISO } from "date-fns";
import axios from "axios";
import { PiTimer } from "react-icons/pi";
import { IoLocationSharp, IoCloseOutline } from "react-icons/io5";
import { FaWindowRestore } from "react-icons/fa";
import Button from "./Button";

const CreateModal = ({
  isOpen,
  onRequestClose,
  selectedDate,
  handleUpdateData,
}) => {
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
    const newStartDate = `${event.target.value}T${eventStartTime}`;
    setNewStartDate(newStartDate);
    if (new Date(event.target.value) > new Date(EndDate)) {
      setEndDate(event.target.value);
      const updatedEndDate = event.target.value;
      const newEndDate = `${updatedEndDate}T${eventEndTime}`;
      setNewEndDate(newEndDate);
    }
  };

  const handleEndDateChange = (event) => {
    setEndDate(event.target.value);
    const updatedEndDate = addDays(new Date(event.target.value), 1);
    const newEndDate = `${format(
      updatedEndDate,
      "yyyy-MM-dd"
    )}T${eventEndTime}`;
    setNewEndDate(newEndDate);
  };

  const handleStartTimeSelect = (time) => {
    const startDateString = `${StartDate}T${time}`;
    const endDateString = `${EndDate}T${eventEndTime}`;

    setEventStartTime(time);
    setNewStartDate(startDateString);

    if (compareAsc(parseISO(startDateString), parseISO(endDateString)) > 0) {
      setEventEndTime(time);
      setNewEndDate(startDateString);
    }
  };

  const handleEndTimeSelect = (time) => {
    let updatedEndDate = addDays(new Date(EndDate), 1);
    const newEndDate = `${format(updatedEndDate, "yyyy-MM-dd")}T${time}`;
    setEventEndTime(time);
    setNewEndDate(newEndDate);
  };

  const handleMemoChange = (e) => {
    setEventMemo(e.replace(/<[^>]*>/g, ""));
  };

  const handlePlaceChange = (e) => {
    setEventPlace(e.target.value);
  };

  function createDate(title, id, startDate, endDate) {
    const specificEvent = {
      title: title,
      id: id,
      start: startDate.split("T")[0],
      end: endDate.split("T")[0],
      color: "#ffc5bf",
    };
    handleUpdateData(specificEvent);
  }

  const saveData = () => {
    const data = {
      eventName: eventName,
      eventStartDate: eventStartDate,
      eventEndDate: eventEndDate,
      eventPlace: eventPlace,
      eventInfo: eventMemo,
    };
    axios.post("/api/calendar", data).then((res) => {
      createDate(
        eventName,
        res.data.data.eventId,
        eventStartDate,
        eventEndDate
      );
      onRequestClose();
    });
  };

  return (
    <Modal
      isOpen={isOpen}
      onRequestClose={onRequestClose}
      className="modal"
      overlayClassName="overlay"
    >
      <div className="modal-header">
        <IoCloseOutline className="close-icon" onClick={onRequestClose} />
      </div>
      <div className="modal-content">
        <TitleInput
          placeholder="제목을 입력하세요"
          value={eventName}
          onChange={handleTitleChange}
        />
        <RowContainer>
          <IconWrapper>
            <FaWindowRestore />
          </IconWrapper>
          <Button content="에코노" />
          <Button content="그룹" />
          <Button content="개인" />
        </RowContainer>
        <RowContainer>
          <TimeIconWrapper>
            <PiTimer />
          </TimeIconWrapper>
          <DateTimeContainer>
            <DateRow>
              시작일 :
              <input
                type="date"
                value={StartDate}
                onChange={handleStartDateChange}
              />
              마감일 :
              <input
                type="date"
                value={EndDate}
                onChange={handleEndDateChange}
                min={StartDate}
              />
            </DateRow>
            <DateRow>
              <TimeSelect onTimeSelect={handleStartTimeSelect} />
              부터
              <TimeSelect onTimeSelect={handleEndTimeSelect} />
              까지
            </DateRow>
          </DateTimeContainer>
        </RowContainer>
        <RowContainer>
          <IconWrapper>
            <IoLocationSharp />
          </IconWrapper>
          <PlaceSelect placeholder="위치 추가" onChange={handlePlaceChange} />
        </RowContainer>
        <EditorBox>
          <ReactQuill placeholder={"설명 추가"} onChange={handleMemoChange} />
        </EditorBox>
        <div style={{ display: "flex", justifyContent: "flex-end" }}>
          <SaveButton onClick={saveData} disabled={!eventName}>
            저장
          </SaveButton>
        </div>
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

const DateRow = styled.div`
  display: flex;
  align-items: center;
  gap: 0.7rem;
`;

const RowContainer = styled.div`
  display: flex;
  align-items: center;
  margin-bottom: 1rem;
`;

const IconWrapper = styled.div`
  margin-right: 0.5rem;
  display: flex;
  align-items: center;
  color: #969696;
  margin-bottom: 0.2rem;
`;

const TimeIconWrapper = styled.div`
  margin-right: 0.5rem;
  display: flex;
  align-items: center;
  color: #606060;
  margin-bottom: 2.5rem;
`;

const DateTimeContainer = styled.div`
  flex: 1;
`;

const PlaceSelect = styled.input`
  border: none;
  width: 100%;
  outline: none;
  padding: 0.5rem 0;
`;
