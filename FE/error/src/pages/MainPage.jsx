import { useState, useEffect } from "react";
import styled from "styled-components";
import EconoCalendar from "../components/EconoCalendar";
import ProfileBar from "../components/SideBar/ProfileBar";
import PublicFilter from "../components/SideBar/publicFilter/PublicFilter";
import IndividualFilter from "../components/SideBar/individualFilter/IndividualFilter";
import GroupFilter from "../components/SideBar/groupFilter/GroupFilter";
import axios from "axios";

const MainPage = () => {
  const [filterIndividualLists, setFilterIndividualLists] = useState([]);
  const [filterGroupLists, setFilterGroupLists] = useState([]);
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [events, setEvents] = useState([]);
  const [filteredEvents, setFilteredEvents] = useState([]);
  const [activeFilters, setActiveFilters] = useState([]);
  const [token, setToken] = useState(null);

  useEffect(() => {
    const storedToken = localStorage.getItem("slackToken");
    setToken(storedToken);

    const isUserLoggedIn = !!storedToken;
    setIsLoggedIn(isUserLoggedIn);

    const uri = isUserLoggedIn
      ? "/api/calendar/all/private"
      : "/api/calendar/all/public";

    axios
      .get(uri, { headers: { Authorization: `Bearer ${storedToken}` } })
      .then((res) => {
        const fetchedEvents = res.data.data.map((event) => ({
          title: event.eventName,
          id: event.eventId,
          start: event.eventStartDate.split("T")[0],
          end: event.eventEndDate.split("T")[0],
          color: event.filterColor,
        }));
        setEvents(fetchedEvents);
      })
      .catch((error) => {
        console.error("Error fetching events:", error);
      });
  }, []);

  useEffect(() => {
    const token = localStorage.getItem("slackToken");
    setIsLoggedIn(!!token);
  }, []);

  const addNewIndividualFilter = (newIndividualFilter) => {
    setFilterIndividualLists([...filterIndividualLists, newIndividualFilter]);
  };

  const addNewGroupFilter = (newGroupFilter) => {
    setFilterGroupLists([...filterGroupLists, newGroupFilter]);
  };
  const updateDeleteFilter = (newFilter) => {
    setFilterIndividualLists(
      filterIndividualLists.filter((filter) => filter.filterId !== newFilter)
    );
  };
  const storedToken = localStorage.getItem("slackToken");

  useEffect(() => {
    axios
      .get("/api/filter", {
        headers: { Authorization: `Bearer ${storedToken}` },
      })
      .then((res) => {
        console.log(res);
        const fetchedFilter = res.data.data.map((filter) => ({
          filterId: filter.filterId,
          filterName: filter.filterName,
          filterColor: filter.filterColor,
        }));
        setFilterIndividualLists(fetchedFilter);
      })
      .catch((err) => {
        console.log("Error fetching events:", err);
      });
  }, []);

  return (
    <div>
      <CalendarPage>
        <SideBar>
          <Logo>ERROR</Logo>
          <LineBox />
          <ScrollableContent>
            <ProfileBar />
            <FilterFrame>
              <PublicFilter />
              {isLoggedIn && (
                <>
                  <GroupFilter
                    filterLists={filterGroupLists}
                    addNewFilter={addNewGroupFilter}
                  />
                  <IndividualFilter
                    filterLists={filterIndividualLists}
                    addNewFilter={addNewIndividualFilter}
                  />
                </>
              )}
            </FilterFrame>
          </ScrollableContent>
        </SideBar>
        <EconoCalendar isLoggedIn={isLoggedIn} setIsLoggedIn={setIsLoggedIn} />
      </CalendarPage>
    </div>
  );
};

export default MainPage;

const SideBar = styled.div`
  width: 15.625rem;
  height: 98.1vh;
  margin-top: 1rem;
  display: flex;
  flex-direction: column;
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
  margin-top: 0.65rem;
`;

const Logo = styled.div`
  font-size: 2rem;
  font-weight: bold;
  margin-left: 1.5rem;
  margin-top: 0.3rem;
  color: #ff9999;
  margin-bottom: 1rem;
`;

const ScrollableContent = styled.div`
  flex-grow: 1;
  overflow-y: auto;
  overflow-x: hidden;

  /* 기본적으로 스크롤바를 숨김 */
  scrollbar-width: thin;
  scrollbar-color: transparent transparent;

  /* 호버 시 스크롤바 표시 */
  &:hover {
    scrollbar-color: #c6c6c6 transparent;
  }

  /* Webkit 브라우저 (Chrome, Safari 등)를 위한 스타일 */
  &::-webkit-scrollbar {
    width: 8px;
  }

  &::-webkit-scrollbar-track {
    background: transparent;
  }

  &::-webkit-scrollbar-thumb {
    background-color: transparent;
    border-radius: 20px;
    border: 3px solid transparent;
  }
`;

const FilterFrame = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
`;
