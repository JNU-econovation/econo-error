import FullCalendar from "@fullcalendar/react";
import dayGridPlugin from "@fullcalendar/daygrid";
import interactionPlugin from "@fullcalendar/interaction";
import styled from "styled-components";
import React, { useEffect } from "react";
import { Calendar } from "@fullcalendar/core";
import CreateModal from "./CreateModal";
import { useState } from "react";
import axios from "axios";

const EconoCalendar = () => {
  const [modalIsOpen, setModalIsOpen] = useState(false); // 모달 상태 관리
  const [selectedDate, setSelectedDate] = useState("");

  const instance = axios.create({
    baseURL: `${import.meta.env.VITE_ERROR_API}`,
  });

  instance.get("/api/calendar/month/2025-05-05").then((res) => {
    console.log(res.data);
  });

  const handleDateClick = (arg) => {
    setSelectedDate(arg.dateStr);

    setModalIsOpen(true); // 날짜 클릭 시 모달 열기
  };

  // useEffect(() => {
  //   console.log(import.meta.env.VITE_ERROR_API);
  //   axios
  //     .get(`${import.meta.env.VITE_ERROR_API}/api/calendar/month/2025-05-05`)
  //     .then((res) => {
  //       console.log(res.data); // 전체 응답 출력
  //     })
  //     .catch((error) => {
  //       console.error("Error fetching data:", error);
  //     });
  // }, []);
  return (
    <>
      <CalendarContainer>
        <FullCalendar
          plugins={[dayGridPlugin, interactionPlugin]}
          locale={"ko"}
          height={"98vh"}
          headerToolbar={{
            left: "today prev title next",
            center: "",
            right: "",
          }}
          events={[]}
          dayCellContent={function (info) {
            var number = document.createElement("a");
            number.classList.add("fc-daygrid-day-number");
            number.innerHTML = info.dayNumberText.replace("일", "");
            if (info.view.type === "dayGridMonth") {
              return {
                html: number.outerHTML,
              };
            }
            return {
              domNodes: [],
            };
          }}
          titleFormat={(date) => {
            const year = date.date.year;
            const month = date.date.month + 1;
            if (month < 10) return year + ".0" + month;
            else return year + "." + month;
          }}
          buttonText={{
            today: "오늘",
          }}
          dateClick={handleDateClick}
        />
      </CalendarContainer>
      <CreateModal
        isOpen={modalIsOpen}
        onRequestClose={() => setModalIsOpen(false)}
        selectedDate={selectedDate} // 클릭한 날짜를 CreateModal에 전달
      />
    </>
  );
};

export default EconoCalendar;

const CalendarContainer = styled.div`
  width: 100%;
  margin-top: 1rem;
  .fc-toolbar-chunk {
    display: flex;
  }

  .fc-prev-button {
    background-color: unset;
    color: #6c757d;
    border: none;
    &:hover {
      background-color: unset;
      color: #6c757d;
      border: none;
    }
  }
  .fc-next-button {
    background-color: unset;
    color: #6c757d;
    border: none;
    &:hover {
      background-color: unset;
      color: #6c757d;
      border: none;
    }
  }

  .fc-today-button {
    background-color: unset;
    color: #595959;
    border: 1px solid #cbcbcb;
    &:hover {
      background-color: unset;
      color: #595959;
      border: 1px solid #cbcbcb;
    }
  }
  .fc-today-button[disabled] {
    background-color: unset;
    color: #595959;
    border: 1px solid #cbcbcb;
  }
  .fc-day-sun a {
    color: red;
    text-decoration: none;
  }
  .fc-daygrid-day-top {
    width: 2rem;
    margin-left: 0.3rem;
  }
  .fc-day-today {
    background: #fff !important;
  }
  .fc-day-today .fc-daygrid-day-top {
    background: #ff9999 !important;
    border-radius: 50% !important;
    color: #fff;
    margin-left: 0.5rem;
    width: 1.7rem;
  }
  .fc-day-today .fc-daygrid-day-frame {
    margin-top: 0.2rem;
  }
  .fc-day-today .fc-daygrid-day-number {
    margin-top: 0.1rem;
    width: 2rem;
  }
  .fc-daygrid-day-number {
    margin-top: 0.3rem;
  }
  .fc-toolbar-title {
    margin-top: 0.2em;
  }
  .fc-scrollgrid-sync-inner {
    border: none;
  }
  .fc-col-header-cell {
    border-right: none;
    border-left: none;
  }
`;
