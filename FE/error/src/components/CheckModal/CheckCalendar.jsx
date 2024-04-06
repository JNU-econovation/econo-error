import React, { useEffect, useState } from "react"; // useState 추가
import Modal from "react-modal";
import "./CheckCalendar.css";
import styled from "styled-components";
import { GoPencil } from "react-icons/go";
import { IoClose } from "react-icons/io5";
import axios from "axios";
import { MdOutlineLocationOn } from "react-icons/md";
import { MdOutlineAutoAwesomeMotion } from "react-icons/md";
import DeletEvent from "./DeleteEvent";

const CheckCalendar = ({
  isOpen,
  onRequestClose,
  selectID,
  events,
  setEvents,
}) => {
  const [event, setEvent] = useState({});
  const Calendarmodify = () => {};

  function createDate(title, startDate, endDate, place, info) {
    const specificEvent = {
      title: title,
      startDate: startDate,
      endDate: endDate,
      place: place,
      info: info,
    };
    setEvent(specificEvent);
  }
  useEffect(() => {
    const instance = axios.create({
      baseURL: `${import.meta.env.VITE_ERROR_API}`,
    });
    instance.get("/api/calendar/" + selectID).then((res) => {
      createDate(
        res.data.data.eventName,
        res.data.data.eventStartDate,
        res.data.data.eventEndDate,
        res.data.data.eventPlace,
        res.data.data.eventInfo
      );
    });
  }, [selectID]);

  /*function date(startDate, endDate) {
    if (startDate.split("T")[0] === endDate.split("T")[0]) return startDate;
    else {
      return `${startDate} - ${endDate}`;
    }
  }*/

  return (
    <Modal
      isOpen={isOpen}
      onRequestClose={onRequestClose}
      className="modal"
      overlayClassName="overlay"
    >
      <ModalBar>
        <button onClick={onRequestClose}>
          <IoClose size="1.2rem" />
        </button>
        <button onClick={Calendarmodify}>
          <GoPencil size="1.2rem" />
        </button>
        <DeletEvent events={events} setEvents={setEvents} selectID={selectID} />
      </ModalBar>

      <ModalContent>
        <Title>
          <span></span>
          <div>{event.title}</div>
        </Title>
        {/*<Date>
          <p>{date(event.startDate, event.endDate)}</p>
  </Date>*/}
        <p>
          <MdOutlineLocationOn style={{ marginRight: "0.5rem" }} />
          {event.place}
        </p>
        <p>
          <MdOutlineAutoAwesomeMotion style={{ marginRight: "0.5rem" }} />
          {event.info}
        </p>
      </ModalContent>
    </Modal>
  );
};

export default CheckCalendar;

const ModalBar = styled.div`
  margin-top: 0.5rem;
  width: 100%;
  display: flex;
  flex-direction: row-reverse;
  outline: none;

  button {
    width: 3rem;
    background-color: #fff;
    border: none;
    svg {
      size: 5rem;
    }
  }
`;

const ModalContent = styled.div`
  padding: 1rem;
  display: flex;
  flex-direction: column;
  span {
    width: 1rem;
    height: 1rem;
    background-color: #beb9ff;
    margin-right: 0.5rem;
  }
  div {
    font-weight: bold;
  }
  p {
    margin-top: 0.6rem;
    margin-right: 0.6rem;
  }
`;

const Title = styled.div`
  display: flex;
`;

/*const Date = styled.div`
  margin-left: 1.55rem;
`;*/
