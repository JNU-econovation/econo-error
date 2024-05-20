import { useEffect, useState, useRef } from "react"; // useState 추가
import Modal from "react-modal";
import "./CheckCalendar.css";
import styled from "styled-components";
import { GoPencil } from "react-icons/go";
import { IoClose } from "react-icons/io5";
import axios from "axios";
import { MdOutlineLocationOn } from "react-icons/md";
import { MdOutlineAutoAwesomeMotion } from "react-icons/md";
import DeleteEvent from "./DeleteEvent";
import { Link } from "react-router-dom";

const CheckCalendar = ({
  isOpen,
  onRequestClose,
  selectID,
  events,
  handleUpdateDeleteData,
  handleDelete,
}) => {
  const [event, setEvent] = useState({});

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

  const isMount = useRef(false);
  useEffect(() => {
    if (!isMount.current) {
      isMount.current = true;
      return;
    }

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

  return (
    <Modal
      isOpen={isOpen}
      onRequestClose={onRequestClose}
      className="CheckModal"
      overlayClassName="overlay"
    >
      <ModalBar>
        <button onClick={onRequestClose}>
          <IoClose size="1.2rem" />
        </button>
        <Link to="/ModifyPage" state={{ selectID: selectID }}>
          <button>
            <GoPencil size="1.2rem" />
          </button>
        </Link>
        <DeleteEvent
          events={events}
          selectID={selectID}
          handleUpdateDeleteData={handleUpdateDeleteData}
          handleDelete={handleDelete}
          onRequestClose={onRequestClose}
        />
      </ModalBar>

      <ModalContent>
        <Title>
          <span></span>
          <div>{event.title}</div>
        </Title>
        <Date>{date(event.startDate, event.endDate)}</Date>
        {event.place && (
          <p>
            <MdOutlineLocationOn style={{ marginRight: "0.5rem" }} />
            {event.place}
          </p>
        )}
        {event.info && (
          <p>
            <MdOutlineAutoAwesomeMotion style={{ marginRight: "0.5rem" }} />
            {event.info}
          </p>
        )}
      </ModalContent>
    </Modal>
  );
};

export default CheckCalendar;

const ModalBar = styled.div`
  margin-top: 0.5rem;
  width: 25rem;
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
  padding: 2rem;
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

const Date = styled.p`
  margin-left: 1.55rem;
  font-size: small;
`;
