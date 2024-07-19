import { useEffect, useState, useRef } from "react";
import { GoPencil } from "react-icons/go";
import { IoClose } from "react-icons/io5";
import { MdOutlineLocationOn } from "react-icons/md";
import { AiOutlineBars } from "react-icons/ai";
import { FaRegCalendar } from "react-icons/fa6";

import { Link } from "react-router-dom";
import Modal from "react-modal";
import styled from "styled-components";
import axios from "axios";
import "./CheckCalendar.css";
import DeleteEvent from "../DeleteEvent";

const CheckCalendar = ({
  isOpen,
  onRequestClose,
  selectID,
  events,
  handleDelete,
  handleUpdateDeleteData,
}) => {
  const [event, setEvent] = useState({});

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

  const isMount = useRef(false);
  useEffect(() => {
    if (!isMount.current) {
      isMount.current = true;
      return;
    }

    axios.get("/api/calendar/" + selectID).then((res) => {
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
          <IoClose size="2rem" color="rgb(95, 99, 104)" />
        </button>
        <StyledModifyIcon>
          <Link to="/ModifyPage" state={{ selectID: selectID }}>
            <button>
              <GoPencil size="1.55rem" color="rgb(95, 99, 104)" />
            </button>
          </Link>
        </StyledModifyIcon>
        <DeleteEvent
          events={events}
          selectID={selectID}
          handleDelete={handleDelete}
          onRequestClose={onRequestClose}
          handleUpdateDeleteData={handleUpdateDeleteData}
        />
      </ModalBar>

      <ModalContent>
        <Title color={event.color}>
          <span></span>
          <div>{event.title}</div>
        </Title>
        <Date>{date(event.startDate, event.endDate)}</Date>
        {event.place && (
          <StyledDetailIcon>
            <MdOutlineLocationOn
              size="1.6rem"
              color="rgb(95, 99, 104)"
              style={{ marginRight: "1.5rem" }}
            />
            <div>{event.place}</div>
          </StyledDetailIcon>
        )}
        {event.info && (
          <StyledDetailIcon>
            <AiOutlineBars
              size="1.6rem"
              color="rgb(95, 99, 104)"
              style={{ marginRight: "1.5rem" }}
            />
            <div> {event.info}</div>
          </StyledDetailIcon>
        )}
        {event.type && (
          <StyledDetailIcon>
            <FaRegCalendar
              size="1.5rem"
              color="rgb(95, 99, 104)"
              style={{ marginRight: "1.5rem" }}
            />
            <div> {event.type}</div>
          </StyledDetailIcon>
        )}
      </ModalContent>
    </Modal>
  );
};

export default CheckCalendar;

const StyledModifyIcon = styled.div`
  margin-top: 0.22rem;
  margin-left: 0.4rem;
`;
const ModalBar = styled.div`
  padding-top: 0.5rem;
  padding-left: 0.375rem;
  padding-right: 0.375rem;
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
  padding-right: 1.75rem;
  padding-left: 1.75rem;
  padding-bottom: 1rem;
  p {
    margin-top: 0.6rem;
    margin-right: 0.6rem;
  }
`;

const Title = styled.div`
  display: flex;
  align-items: center;
  margin-left: 0.3rem;
  span {
    width: 1rem;
    height: 1rem;
    background-color: ${(props) => props.color || "#beb9ff"};
    margin-right: 1.6rem;
    border-radius: 0.3rem;
  }
  div {
    font-size: 1.6rem;
  }
`;

const Date = styled.p`
  margin-left: 2.75rem;
  margin-bottom: 1.8rem;
`;
const StyledDetailIcon = styled.div`
  margin-top: 1.2rem;
  margin-bottom: 1.2rem;

  display: flex;
  align-items: center;
  div {
    font-size: 1.1em;
  }
`;
