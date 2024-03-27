import FullCalendar from "@fullcalendar/react";
import dayGridPlugin from "@fullcalendar/daygrid";
import styled from "styled-components";
const EconoCalendar = () => {
  return (
    <>
      <FullCalendar plugins={[dayGridPlugin]} initialView="dayGridMonth" />
    </>
  );
};

export default EconoCalendar;
