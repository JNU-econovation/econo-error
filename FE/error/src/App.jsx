import "./App.css";
import EconoCalendar from "./components/EconoCalendar";
import styled from "styled-components";
import CreateModal from "./components/CreateModal";

function App() {
  return (
    <CalendarPage>
      <SideBar>
        <Logo>ERROR</Logo>
        <LineBox />
      </SideBar>
      <EconoCalendar />
    </CalendarPage>
  );
}

export default App;

const SideBar = styled.div`
  width: 20vw;
  height: 98.1vh;
  margin-top: 1rem;
`;

const CalendarPage = styled.div`
  display: flex;
  width: 100%;
`;

const LineBox = styled.div`
  width: 100%;
  height: 1.25rem;
  border: 1px solid #ddd;
  border-right: none;

  margin-top: 1.63em;
`;

const Logo = styled.div`
  font-size: 2rem;
  font-weight: bold;
  margin-left: 1.5rem;
  margin-top: 0.3rem;
  color: #ff9999;
  margin-bottom: 1rem;
`;
