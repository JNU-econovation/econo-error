import React, { useEffect, useState } from "react"; // useState 추가
import Modal from "react-modal";
import "./CheckCalendar.css";
import styled from "styled-components";
import { RiDeleteBinLine } from "react-icons/ri";
import { GoPencil } from "react-icons/go";
import { IoClose } from "react-icons/io5";
import axios from "axios";

const CheckCalendar = ({ isOpen, onRequestClose, selectID }) => {
  const [event, setEvent] = useState({});
  const Calendarmodify = () => {};
  const calendarDelete = () => {};

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

  return (
    <Modal
      isOpen={isOpen}
      onRequestClose={onRequestClose}
      className="modal"
      overlayClassName="overlay"
    >
      <ModalBar>
        <button onClick={onRequestClose}>
          <IoClose />
        </button>
        <button onClick={Calendarmodify}>
          <GoPencil />
        </button>
        <button onClick={calendarDelete}>
          <RiDeleteBinLine />
        </button>
      </ModalBar>
      <ModalContent>
        <p>{event.title}</p>
        {/* {(event.startDate).split("T")[0] === event.endDate.split("T")[0] ? (
          <>
            <p>{event.startDate.split("T")[0]}</p>
            <p>{event.startDate.split("T")[1]}</p>
          </>
        ) : (
          <>
            <p>{event.startDate.split("T")[0] - event.endDate.split("T")[0]}</p>
            <p>{event.startDate.split("T")[1] - event.endDate.split("T")[1]}</p>
          </>
        )} */}
        <p>{event.place}</p>
        <p>{event.info}</p>
      </ModalContent>
    </Modal>
  );
};

export default CheckCalendar;

const ModalBar = styled.div`
  width: 100%;
  display: flex;
  flex-direction: row-reverse;
  outline: none;

  button {
    width: 3rem;
    background-color: aliceblue;
    border: none;
  }
`;

const ModalContent = styled.div`
  display: flex;
  flex-direction: column;
`;
