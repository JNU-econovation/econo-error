import { useState, useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { IoMdClose } from "react-icons/io";
import ReactQuill from "react-quill";
import axios from "axios";
import TimeSelect from "../components/common/TimeSelect";
import * as S from "../styles/pages/CalendarModify";
const CalendarModify = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const selectID = location.state.selectID;
  const storedToken = localStorage.getItem("slackToken");

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
      .put(
        "/api/calendar/" + selectID,
        {
          headers: { Authorization: `Bearer ${storedToken}` },
        },
        eventData
      )
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
    <S.Layout>
      <S.HeaderContainer>
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
        <S.TitleInput
          placeholder="제목"
          value={modifyName}
          onChange={handleTitleChange}
        />
      </S.HeaderContainer>
      <S.DetailInfoContainer>
        <S.DateRowBox>
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
        </S.DateRowBox>
        <S.DateRowBox>
          <TimeSelect
            onTimeSelect={handleStartTimeSelect}
            value={modifyStartTime}
          />
          <TimeSelect
            onTimeSelect={handleEndTimeSelect}
            value={modifyEndTime}
          />
        </S.DateRowBox>
        <S.PlaceInput
          placeholder="위치 추가"
          value={modifyPlace}
          onChange={handlePlaceChange}
        ></S.PlaceInput>
        <S.MemoWrapper>
          <ReactQuill
            placeholder={"설명 추가"}
            onChange={handleInfoChange}
            value={modifyInfo}
          />
        </S.MemoWrapper>
        <div style={{ display: "flex", justifyContent: "flex-end" }}>
          <S.SaveBtn onClick={modifyData}>저장</S.SaveBtn>
        </div>
      </S.DetailInfoContainer>
    </S.Layout>
  );
};

export default CalendarModify;
