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
        />
      </CalendarContainer>
    </>
  );
};

export default EconoCalendar;

const CalendarContainer = styled.div`
  margin-left: 17.7rem;
  margin-top: 1.5rem;
`;
