import { useState, useEffect, useMemo } from "react";
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
  const [activeFilters, setActiveFilters] = useState(new Set());
  const [token, setToken] = useState(null);

  // 필터링된 이벤트를 계산
  const filteredEvents = useMemo(() => {
    if (activeFilters.size === 0) return events;
    return events.filter((event) => activeFilters.has(event.filterId));
  }, [events, activeFilters]);

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
          filterId: event.filterId,
        }));
        setEvents(fetchedEvents);
        // 모든 필터를 초기에 활성화
        setActiveFilters(new Set(fetchedEvents.map((event) => event.filterId)));
      })
      .catch((error) => {
        console.error("Error fetching events:", error);
      });
  }, []);

  const handleFilterChange = (filterId, isChecked) => {
    setActiveFilters((prevFilters) => {
      const newFilters = new Set(prevFilters);
      if (isChecked) {
        newFilters.add(filterId);
      } else {
        newFilters.delete(filterId);
      }
      return newFilters;
    });
  };

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
    // 삭제된 필터를 activeFilters에서도 제거
    setActiveFilters((prevFilters) => {
      const newFilters = new Set(prevFilters);
      newFilters.delete(newFilter);
      return newFilters;
    });
  };

  useEffect(() => {
    const storedToken = localStorage.getItem("slackToken");
    axios
      .get("/api/filter", {
        headers: { Authorization: `Bearer ${storedToken}` },
      })
      .then((res) => {
        const fetchedFilter = res.data.data.map((filter) => ({
          filterId: filter.filterId,
          filterName: filter.filterName,
          filterColor: filter.filterColor,
        }));
        setFilterIndividualLists(fetchedFilter);
        // 모든 개인 필터를 초기에 활성화
        setActiveFilters(
          new Set(fetchedFilter.map((filter) => filter.filterId))
        );
      })
      .catch((err) => {
        console.log("Error fetching filters:", err);
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
              <PublicFilter
                filterLists={filterGroupLists}
                addNewFilter={addNewGroupFilter}
              />
              {isLoggedIn && (
                <>
                  <GroupFilter
                    filterLists={filterGroupLists}
                    addNewFilter={addNewGroupFilter}
                  />
                  <IndividualFilter
                    filterLists={filterIndividualLists}
                    addNewFilter={addNewIndividualFilter}
                    onFilterChange={handleFilterChange}
                    updateDeleteFilter={updateDeleteFilter}
                  />
                </>
              )}
            </FilterFrame>
          </ScrollableContent>
        </SideBar>
        <EconoCalendar
          isLoggedIn={isLoggedIn}
          setIsLoggedIn={setIsLoggedIn}
          events={filteredEvents}
          setEvents={setEvents}
          setToken={setToken}
        />
      </CalendarPage>
    </div>
  );
};

export default MainPage;

// ... 나머지 styled-components 코드는 그대로 유지

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
