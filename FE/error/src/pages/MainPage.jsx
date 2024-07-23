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

  useEffect(() => {
    axios
      .get("/api/calendar/filter/all")
      .then((res) => {
        const fetchedFilter = res.data.data.map((filter) => ({
          filterId: filter.filterId,
          filterName: filter.filterName,
          filterColor: filter.filterColor,
        }));
        /*[
        {
          filterId: 1,
          filterName: 'hi',
          filterColor: 'pink'
        },
        {
          filterId: 2,
          filterName: 'hi',
          filterColor: 'pink'
        }
      ]*/
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
        </SideBar>
        <EconoCalendar isLoggedIn={isLoggedIn} setIsLoggedIn={setIsLoggedIn} />
      </CalendarPage>
    </div>
  );
};

export default MainPage;

const SideBar = styled.div`
  width: 250px;
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

const FilterFrame = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
`;
