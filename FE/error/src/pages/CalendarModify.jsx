import { useState, useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { IoMdClose } from "react-icons/io";
import styled from "styled-components";
import ReactQuill from "react-quill";
import axios from "axios";
import TimeSelect from "../components/TimeSelect";

const CalendarModify = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const selectID = location.state.selectID;

  const [modifyName, setModifyName] = useState("");
  const [modifyStartDate, setModifyStartDate] = useState("");
  const [modifyEndDate, setModifyEndDate] = useState("");
  const [modifyInfo, setModifyInfo] = useState("");
  const [modifyStartTime, setModifyStartTime] = useState("");
  const [modifyEndTime, setModifyEndTime] = useState("");
  const [modifyPlace, setModifyPlace] = useState("");

  const handleTitleChange = (e) => {
    setModifyName(e.target.value);
  };
  const handleStartDateChange = (e) => {
    setModifyStartDate(e.target.value);
  };

  const handleEndDateChange = (e) => {
    setModifyEndDate(e.target.value);
  };

  const handleStartTimeSelect = (time) => {
    setModifyStartTime(time);
  };

  const handleEndTimeSelect = (time) => {
    setModifyEndTime(time);
  };

  const handleInfoChange = (e) => {
    setModifyInfo(e.replace(/<[^>]*>/g, ""));
  };

  const handlePlaceChange = (e) => {
    setModifyPlace(e.target.value);
  };

  useEffect(() => {
    axios.get("/api/calendar/" + selectID).then((res) => {
      const event = res.data.data;
      const title = event.eventName;
      const startDate = event.eventStartDate.split("T")[0];
      const startTime = event.eventStartDate.split("T")[1];
      const endDate = event.eventEndDate.split("T")[0];
      const endTime = event.eventEndDate.split("T")[1];
      const info = event.eventInfo;
      const place = event.eventPlace;
      setModifyName(title);
      setModifyStartDate(startDate);
      setModifyEndDate(endDate);
      setModifyStartTime(startTime);
      setModifyEndTime(endTime);
      setModifyInfo(info);
      setModifyPlace(place);
    });
  }, [selectID]);

  const modifyData = () => {
    const eventData = {
      eventName: modifyName,
      eventStartDate: modifyStartDate + "T" + modifyStartTime,
      eventEndDate: modifyEndDate + "T" + modifyEndTime,
      eventInfo: modifyInfo,
      eventPlace: modifyPlace,
    };
    axios
      .put("/api/calendar/" + selectID, eventData)
      .then((res) => {
        goBack();
      })
      .catch((error) => {
        console.error(error);
      });
  };
  const goBack = () => {
    navigate(-1);
  };
  return (
    <Box>
      <Header>
        <button
          onClick={goBack}
          style={{
            backgroundColor: "#fff",
            border: "none",
            marginTop: "0.2rem",
          }}
        >
          <IoMdClose size="1.6rem" />
        </button>
        <TitleInput
          placeholder="제목"
          value={modifyName}
          onChange={handleTitleChange}
        />
      </Header>
      <ModifyFrame>
        <DateRow>
          <input
            type="date"
            value={modifyStartDate}
            onChange={handleStartDateChange}
          />
          <input
            type="date"
            value={modifyEndDate}
            onChange={handleEndDateChange}
          />
        </DateRow>
        <DateRow>
          <TimeSelect
            onTimeSelect={handleStartTimeSelect}
            value={modifyStartTime}
          />
          <TimeSelect
            onTimeSelect={handleEndTimeSelect}
            value={modifyEndTime}
          />
        </DateRow>
        <PlaceSelect
          placeholder="위치 추가"
          value={modifyPlace}
          onChange={handlePlaceChange}
        ></PlaceSelect>
        <EditorBox>
          <ReactQuill
            placeholder={"설명 추가"}
            onChange={handleInfoChange}
            value={modifyInfo}
          />
        </EditorBox>
        <div style={{ display: "flex", justifyContent: "flex-end" }}>
          <SaveButton onClick={modifyData}>저장</SaveButton>
        </div>
      </ModifyFrame>
    </Box>
  );
};

export default CalendarModify;

const Box = styled.div`
  width: 50rem;
`;
const ModifyFrame = styled.div`
  margin-left: 4rem;
`;
const TitleInput = styled.input`
  width: 100%;
  height: 2rem;
  margin-bottom: 2rem;
  margin-top: 2rem;
  margin-left: 0.7rem;
  font-size: 1.5rem;
  border: none;
  border-bottom: 1px solid #495057;
  outline: none;
`;
const Header = styled.div`
  display: flex;
  margin-left: 1rem;
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
  margin: 1.1rem 0;
`;

const DateRow = styled.div`
  display: flex;
  align-items: center;
  gap: 0.7rem;
`;
