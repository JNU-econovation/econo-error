import { useState, useEffect, useMemo } from "react";
import EconoCalendar from "../components/EconoCalendar";
import ProfileBar from "../components/SideBar/ProfileBar";
import PublicFilter from "../components/SideBar/publicFilter/PublicFilter";
import IndividualFilter from "../components/SideBar/individualFilter/IndividualFilter";
import GroupFilter from "../components/SideBar/groupFilter/GroupFilter";
import axios from "axios";
import * as S from "../styles/pages/MainPage";

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
    <S.Layout>
      <S.SideBarContainer>
        <S.LogoBox>ERROR</S.LogoBox>
        <S.LineBox />
        <S.ScrollableWrapper>
          <ProfileBar />
          <S.FilterFrameBox>
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
          </S.FilterFrameBox>
        </S.ScrollableWrapper>
      </S.SideBarContainer>
      <EconoCalendar
        isLoggedIn={isLoggedIn}
        setIsLoggedIn={setIsLoggedIn}
        events={filteredEvents}
        setEvents={setEvents}
        setToken={setToken}
      />
    </S.Layout>
  );
};

export default MainPage;
