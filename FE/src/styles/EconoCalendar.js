import styled from "styled-components";

export const FulCalendarWrapper = styled.div`
  width: 100%;
  margin-top: 1rem;
  .fc-toolbar-chunk {
    display: flex;
    .fc-toolbar-chunk > :last-child {
      margin-right: 1rem;
    }
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
  .fc-prev-button:focus,
  .fc-next-button:focus {
    outline: none;
    box-shadow: none;
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
  .fc-daygrid-day-top {
    width: 2rem;
    margin-left: 0.3rem;
  }
  .fc-day-today {
    background-color: #ffffff !important;
  }
  .fc-day-today .fc-daygrid-day-top {
    background: #ff9999 !important;
    border-radius: 50% !important;
    color: #fff;
    margin-left: 0.5rem;
    width: 1.53rem;
    display: flex;
    justify-content: center;
  }
  .fc-day-today .fc-daygrid-day-frame {
    margin-top: 0.2rem;
  }
  .fc-day-today .fc-daygrid-day-number {
    margin-top: 0.1rem;
    width: 2rem;
  }
  .fc-daygrid-day-number {
    margin-top: 0.3rem;
    margin-left: -0.06rem;
  }
  .fc-toolbar-title {
    margin-top: 0.2em;
  }
  .fc-scrollgrid-sync-inner {
    border: none;
  }
  .fc-col-header-cell {
    border-right: none;
    border-left: none;
  }

  .fc-createDateButton-button {
    background-color: #fff;
    border-color: #cbcbcb;
    color: #595959;
    margin-right: 1rem;
  }

  .fc-loginLogoutButton-button {
    background-color: #fff;
    border-color: #cbcbcb;
    color: #595959;
    margin-right: 1rem;
  }
  .fc-event-title-container {
    height: 1.3rem;
    display: flex;
    align-items: center;
    font-size: 0.95rem;
    margin-left: 0.3rem;
  }
  .fc-direction-ltr .fc-toolbar > * > :not(:first-child) {
    margin-left: 0.5rem;
  }
`;
