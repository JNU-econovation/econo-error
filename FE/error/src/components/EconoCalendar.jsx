import { useEffect, useState } from "react";
import toast, { Toaster } from "react-hot-toast";
import FullCalendar from "@fullcalendar/react";
import dayGridPlugin from "@fullcalendar/daygrid";
import styled from "styled-components";
import interactionPlugin from "@fullcalendar/interaction";
import axios from "axios";
import CreateModal from "./scheduleCreate/CreateModal";
import CheckCalendar from "./scheduleCheck/CheckCalendar";

const EconoCalendar = () => {
  const [events, setEvents] = useState([]);
  const [selectID, setSelectID] = useState("");
  const [checkModalIsOpen, setCheckModalIsOpen] = useState(false);
  const [createModalIsOpen, setCreateModalIsOpen] = useState(false);
  const [selectedDate, setSelectedDate] = useState("");

  const handleDelete = () => {
    toast("일정이 삭제되었습니다", {
      style: {
        backgroundColor: "#535353",
        color: "#fff",
      },
    });
  };
  const handleEventClick = (info) => {
    setSelectID(info.event._def.publicId);
    setCheckModalIsOpen(true);
  };
  const handleDateClick = (arg) => {
    setSelectedDate(arg.dateStr);
    setCreateModalIsOpen(true);
  };

  const getCurrentDate = () => {
    const today = new Date();
    const year = today.getFullYear();
    const month = ("0" + (today.getMonth() + 1)).slice(-2);
    const day = ("0" + today.getDate()).slice(-2);
    return `${year}-${month}-${day}`;
  };

  useEffect(() => {
    axios
      .get("/api/calendar/all")
      .then((res) => {
        const fetchedEvents = res.data.data.map((event) => ({
          title: event.eventName,
          id: event.eventId,
          start: event.eventStartDate.split("T")[0],
          end: event.eventEndDate.split("T")[0],
          color: "#FFC0CB",
        }));
        setEvents(fetchedEvents);
      })
      .catch((error) => {
        console.error("Error fetching events:", error);
      });
  }, []);

  const handleUpdateData = (newData) => {
    setEvents((preEvents) => [...preEvents, newData]);
  };

  const handleUpdateDeleteData = (newData) => {
    setEvents(events.filter((event) => event.id !== parseInt(newData)));
  };
  return (
    <>
      <CalendarContainer>
        <FullCalendar
          plugins={[dayGridPlugin, interactionPlugin]}
          locale="ko"
          height="98vh"
          dayMaxEventRows={true}
          editable={true}
          moreLinkText={function (num) {
            return "+" + num + "개 더보기";
          }}
          customButtons={{
            createDateButton: {
              text: "일정 생성",
              click: function () {
                setSelectedDate(getCurrentDate());
                setCreateModalIsOpen(true);
              },
            },
          }}
          views={{
            timeGrid: {
              dayMaxEventRows: 6,
            },
          }}
          headerToolbar={{
            left: "today prev title next",
            center: "",
            right: "createDateButton",
          }}
          events={events}
          eventDisplay={"block"}
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
          eventClick={handleEventClick}
          dateClick={handleDateClick}
        />
      </CalendarContainer>
      <CheckCalendar
        isOpen={checkModalIsOpen}
        onRequestClose={() => setCheckModalIsOpen(false)}
        selectID={selectID}
        handleDelete={handleDelete}
        handleUpdateDeleteData={handleUpdateDeleteData}
      />
      <CreateModal
        isOpen={createModalIsOpen}
        onRequestClose={() => setCreateModalIsOpen(false)}
        selectedDate={selectedDate}
        handleUpdateData={handleUpdateData}
      />
      <Toaster position="bottom-center" reverseOrder={false} />
    </>
  );
};

export default EconoCalendar;

const CalendarContainer = styled.div`
  width: 100%;
  margin-top: 1rem;
  .fc-toolbar-chunk {
    display: flex;
    .fc-toolbar-chunk > :last-child {
      margin-right: 1rem;
    }
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
  .fc-prev-button:focus,
  .fc-next-button:focus {
    outline: none;
    box-shadow: none;
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
    background-color: #ffffff !important;
  }
  .fc-day-today .fc-daygrid-day-top {
    background: #ff9999 !important;
    border-radius: 50% !important;
    color: #fff;
    margin-left: 0.5rem;
    width: 1.53rem;
    display: flex;
    justify-content: center;
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
    margin-left: -0.06rem;
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

  .fc-createDateButton-button {
    background-color: #fff;
    border-color: #cbcbcb;
    color: #595959;
    margin-right: 1rem;
  }
  .fc-event-title-container {
    height: 1.3rem;
    display: flex;
    align-items: center;
    font-size: 0.95rem;
    margin-left: 0.3rem;
  }
`;
