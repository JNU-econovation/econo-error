import { useState, useEffect } from "react";
import { format, addDays, compareAsc, parseISO } from "date-fns";
import axios from "axios";
import Modal from "react-modal";
import styled from "styled-components";
import ReactQuill from "react-quill";
import TimeSelect from "../../utils/TimeSelect";
import { PiTimer } from "react-icons/pi";
import { IoLocationSharp, IoCloseOutline } from "react-icons/io5";
import { FaWindowRestore } from "react-icons/fa";
import Button from "../Button";
import "./CreateModal.css";

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
  const [selectedFilter, setSelectedFilter] = useState(null);
  const [activeDropdown, setActiveDropdown] = useState(null);

  useEffect(() => {
    if (isOpen && selectedDate) {
      setEventName("");
      setStartDate(selectedDate);
      setEndDate(selectedDate);
      setEventInfo("");
      setEventPlace("");
      setEventMemo("");
      setEventStartTime("00:00"); // 초기화 추가
      setEventEndTime("00:00"); // 초기화 추가
      setNewStartDate(selectedDate + "T00:00"); // 수정
      setNewEndDate(selectedDate + "T00:00"); // 수정

      setSelectedFilter(null);
      setActiveDropdown(null);
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
    setEventStartTime(time);
    setNewStartDate(`${StartDate}T${time}`);

    // 시작 시간이 종료 시간보다 늦을 경우, 종료 시간을 시작 시간과 같게 설정
    if (StartDate === EndDate && time > eventEndTime) {
      setEventEndTime(time);
      setNewEndDate(`${EndDate}T${time}`);
    }
  };

  const handleEndTimeSelect = (time) => {
    if (StartDate === EndDate && time < eventStartTime) {
      // 종료 시간이 시작 시간보다 이를 경우, 시작 시간을 종료 시간과 같게 설정
      setEventStartTime(time);
      setNewStartDate(`${StartDate}T${time}`);
    }

    setEventEndTime(time);
    setNewEndDate(`${EndDate}T${time}`);
  };

  const handleMemoChange = (e) => {
    setEventMemo(e.replace(/<[^>]*>/g, ""));
  };

  const handlePlaceChange = (e) => {
    setEventPlace(e.target.value);
  };

  const toggleDropdown = (dropdownName) => {
    setActiveDropdown(activeDropdown === dropdownName ? null : dropdownName);
  };

  const handleFilterSelect = (category, filter) => {
    setSelectedFilter({ category, filter });
    setActiveDropdown(null);
  };

  const getButtonContent = (category) => {
    if (selectedFilter && selectedFilter.category === category) {
      return selectedFilter.filter;
    }
    switch (category) {
      case "econo":
        return "에코노";
      case "group":
        return "그룹";
      case "personal":
        return "개인";
      default:
        return "";
    }
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
      eventCategory: {
        econo: selectedFilter.econo,
        group: selectedFilter.group,
        personal: selectedFilter.personal,
      },
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
          {["econo", "group", "personal"].map((category) => (
            <DropdownContainer key={category}>
              <Button
                content={getButtonContent(category)}
                onClick={() => toggleDropdown(category)}
                isActive={
                  selectedFilter && selectedFilter.category === category
                }
              />
              {activeDropdown === category && (
                <DropdownMenu>
                  {category === "econo" && (
                    <>
                      <DropdownItem
                        onClick={() => handleFilterSelect(category, "공식행사")}
                      >
                        공식행사
                      </DropdownItem>
                      <DropdownItem
                        onClick={() => handleFilterSelect(category, "주간발표")}
                      >
                        주간발표
                      </DropdownItem>
                    </>
                  )}
                  {category === "group" && (
                    <>
                      <DropdownItem
                        onClick={() =>
                          handleFilterSelect(category, "그룹필터1")
                        }
                      >
                        그룹필터1
                      </DropdownItem>
                      <DropdownItem
                        onClick={() =>
                          handleFilterSelect(category, "그룹필터2")
                        }
                      >
                        그룹필터2
                      </DropdownItem>
                    </>
                  )}
                  {category === "personal" && (
                    <>
                      <DropdownItem
                        onClick={() =>
                          handleFilterSelect(category, "개인필터1")
                        }
                      >
                        개인필터1
                      </DropdownItem>
                      <DropdownItem
                        onClick={() =>
                          handleFilterSelect(category, "개인필터2")
                        }
                      >
                        개인필터2
                      </DropdownItem>
                    </>
                  )}
                </DropdownMenu>
              )}
            </DropdownContainer>
          ))}
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
              <TimeSelect
                onTimeSelect={handleStartTimeSelect}
                currentTime={eventStartTime}
              />
              부터
              <TimeSelect
                onTimeSelect={handleEndTimeSelect}
                currentTime={eventEndTime}
                minTime={StartDate === EndDate ? eventStartTime : undefined}
              />
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
  color: #969696;
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

const DropdownContainer = styled.div`
  position: relative;
  margin-right: 10px;
`;

const DropdownMenu = styled.div`
  position: absolute;
  top: 100%;
  left: 0;
  padding: 0.3rem 0;
  background-color: white;
  border: 1px solid #ddd;
  border-radius: 4px;
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15), 0 3px 6px rgba(0, 0, 0, 0.1);
  z-index: 1000;
  min-width: 5rem;
`;

const DropdownItem = styled.div`
  padding: 10px;
  cursor: pointer;
  white-space: nowrap;
  &:hover {
    background-color: #f5f5f5;
  }
`;
