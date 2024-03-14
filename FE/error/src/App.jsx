import "./App.css";
import FullCalendar from "@fullcalendar/react";
import dayGridPlugin from "@fullcalendar/daygrid";
function App() {
  return (
    <>
      {" "}
      <FullCalendar plugins={[dayGridPlugin]} initialView="dayGridMonth" />
    </>
  );
}

export default App;
