import { useState } from "react";
import toast, { Toaster } from "react-hot-toast";
import FullCalendar from "@fullcalendar/react";
import dayGridPlugin from "@fullcalendar/daygrid";
import styled from "styled-components";
import interactionPlugin from "@fullcalendar/interaction";
import CreateModal from "./scheduleCreate/CreateModal";
import CheckCalendar from "./scheduleCheck/CheckCalendar";
import * as S from "../styles/EconoCalendar";

const EconoCalendar = ({
  isLoggedIn,
  setIsLoggedIn,
  setEvents,
  events,
  setToken,
}) => {
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
    if (isLoggedIn) {
      setSelectedDate(arg.dateStr);
      setCreateModalIsOpen(true);
    }
  };

  const getCurrentDate = () => {
    const today = new Date();
    const year = today.getFullYear();
    const month = ("0" + (today.getMonth() + 1)).slice(-2);
    const day = ("0" + today.getDate()).slice(-2);
    return `${year}-${month}-${day}`;
  };

  const handleUpdateData = (newData) => {
    setEvents((preEvents) => [...preEvents, newData]);
  };

  const handleUpdateDeleteData = (newData) => {
    setEvents(events.filter((event) => event.id !== parseInt(newData)));
  };

  const handleLoginLogout = () => {
    if (isLoggedIn) {
      localStorage.removeItem("slackToken");
      setIsLoggedIn(false);
      setToken(null);
      window.location.reload();
      // } else {
      //   const newToken = "dummyToken" + Math.random().toString(36).substr(2, 9); // 임의의 토큰 생성
      //   localStorage.setItem("slackToken", newToken);
      //   setToken(newToken);
      //   setIsLoggedIn(true);
      // }
      //TODO: 추후 아래 코드로 변경
      //}
    } else {
      window.location.href = "/login";
    }
  };

  return (
    <>
      <S.FulCalendarWrapper>
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
            ...(isLoggedIn && {
              createDateButton: {
                text: "일정 생성",
                click: function () {
                  setSelectedDate(getCurrentDate());
                  setCreateModalIsOpen(true);
                },
              },
            }),
            loginLogoutButton: {
              text: isLoggedIn ? "로그아웃" : "로그인",
              click: handleLoginLogout,
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
            right: isLoggedIn
              ? "loginLogoutButton createDateButton"
              : "loginLogoutButton",
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
      </S.FulCalendarWrapper>
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
