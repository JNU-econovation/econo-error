import FullCalendar from "@fullcalendar/react";
import dayGridPlugin from "@fullcalendar/daygrid";
import styled from "styled-components";
import { Calendar } from "@fullcalendar/core";
const EconoCalendar = () => {
  return (
    <>
      <CalendarContainer>
        <FullCalendar
          plugins={[dayGridPlugin]}
          initialView="dayGridMonth"
          height={"54.64rem"}
          headerToolbar={{
            left: "today prev title next",
            center: "",
            right: "",
          }} //오늘/arrow left,right버튼 클릭했을때 css에 변화가 생김(해결 못 함)
          // weekday를 한글로 바꾼 후 색상변경
          //ERROR로그 집어넣어야 됨 (배치가 잘 안 됨)
          titleFormat={(date) => {
            const year = date.date.year;
            const month = date.date.month + 1;
            if (month < 10) return year + ".0" + month;
            else return year + "." + month;
          }}
          buttonText={{
            today: "오늘",
          }}
        />
      </CalendarContainer>
    </>
  );
};

export default EconoCalendar;

const CalendarContainer = styled.div`
  margin-left: 17.7rem;
  margin-top: 1.5rem;
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
  //date 왼쪽 정렬
  .fc-daygrid-day-top {
    width: 1.5rem;
  }
  //오늘 날짜 배경색 살색에서 하얀색
  .fc-day-today {
    background: #fff !important;
  }
  //오늘 date 동그라미 근데 피그마처럼 margin-top을 주고 싶은데 해결이 안 됨
  .fc-day-today .fc-daygrid-day-top {
    background: #ff9999 !important;
    border-radius: 70% !important;
    color: #fff;
  }
`;
