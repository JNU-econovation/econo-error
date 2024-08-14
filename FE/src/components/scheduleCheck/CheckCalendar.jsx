import { useEffect, useState } from "react";
import { GoPencil } from "react-icons/go";
import { IoClose } from "react-icons/io5";
import { MdOutlineLocationOn } from "react-icons/md";
import { AiOutlineBars } from "react-icons/ai";
import { FaRegCalendar } from "react-icons/fa6";
import { Link } from "react-router-dom";
import axios from "axios";
import DeleteEvent from "../DeleteEvent";
import * as S from "../../styles/scheduleCheck/CheckCalendar";

const CheckCalendar = ({
  isOpen,
  onRequestClose,
  selectID,
  events,
  handleDelete,
  handleUpdateDeleteData,
}) => {
  const [event, setEvent] = useState({});
  const storedToken = localStorage.getItem("slackToken");

  function createDate(title, startDate, endDate, place, info, type, color) {
    const specificEvent = {
      title: title,
      startDate: startDate,
      endDate: endDate,
      place: place,
      info: info,
      type: type,
      color: color,
    };
    setEvent(specificEvent);
  }

  useEffect(() => {
    if (isOpen && selectID) {
      setEvent({}); // 새로운 데이터 로딩 전에 event 상태 초기화

      axios
        .get("/api/calendar/" + selectID, {
          headers: { Authorization: `Bearer ${storedToken}` },
        })
        .then((res) => {
          createDate(
            res.data.data.eventName,
            res.data.data.eventStartDate,
            res.data.data.eventEndDate,
            res.data.data.eventPlace,
            res.data.data.eventInfo,
            res.data.data.eventType,
            res.data.data.filterColor
          );
        });
    }
  }, [selectID, isOpen, storedToken]);

  function date(startDate, endDate) {
    if (!startDate && !endDate) return "날짜 정보 없음";
    if (startDate.split("T")[0] === endDate.split("T")[0]) {
      if (startDate === endDate) {
        return `${startDate.split("T")[0]} ${startDate.split("T")[1]}`;
      } else
        return `${startDate.split("T")[0]} ${startDate.split("T")[1]}~${
          endDate.split("T")[1]
        }`;
    } else
      return `${startDate.split("T")[0]} ${startDate.split("T")[1]} - ${
        endDate.split("T")[0]
      } ${endDate.split("T")[1]}`;
  }

  const handleRequestClose = () => {
    setEvent({});
    onRequestClose();
  };

  return (
    <S.ModalLayout
      isOpen={isOpen}
      onRequestClose={handleRequestClose}
      overlayClassName="overlay"
    >
      <S.HeaderContainer>
        <button onClick={handleRequestClose}>
          <IoClose size="2rem" color="rgb(95, 99, 104)" />
        </button>
        <S.ModifyIconWrapper>
          <Link to="/ModifyPage" state={{ selectID: selectID }}>
            <button>
              <GoPencil size="1.55rem" color="rgb(95, 99, 104)" />
            </button>
          </Link>
        </S.ModifyIconWrapper>
        <DeleteEvent
          events={events}
          selectID={selectID}
          handleDelete={handleDelete}
          onRequestClose={handleRequestClose}
          handleUpdateDeleteData={handleUpdateDeleteData}
        />
      </S.HeaderContainer>
      <S.DetailedInfoContainer>
        <S.TitleBox color={event.color}>
          <span></span>
          <div>{event.title}</div>
        </S.TitleBox>
        <S.DateParagraph>
          {date(event.startDate, event.endDate)}
        </S.DateParagraph>
        {event.place && (
          <S.DetailIconWrapper>
            <MdOutlineLocationOn
              size="1.6rem"
              color="rgb(95, 99, 104)"
              style={{ marginRight: "1.5rem" }}
            />
            <div>{event.place}</div>
          </S.DetailIconWrapper>
        )}
        {event.info && (
          <S.DetailIconWrapper>
            <AiOutlineBars
              size="1.6rem"
              color="rgb(95, 99, 104)"
              style={{ marginRight: "1.5rem" }}
            />
            <div> {event.info}</div>
          </S.DetailIconWrapper>
        )}
        {event.type && (
          <S.DetailIconWrapper>
            <FaRegCalendar
              size="1.5rem"
              color="rgb(95, 99, 104)"
              style={{ marginRight: "1.5rem" }}
            />
            <div> {event.type}</div>
          </S.DetailIconWrapper>
        )}
      </S.DetailedInfoContainer>
    </S.ModalLayout>
  );
};

export default CheckCalendar;
