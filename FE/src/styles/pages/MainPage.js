import styled from "styled-components";

export const SideBarContainer = styled.div`
  width: 15.625rem;
  height: 98.1vh;
  margin-top: 1rem;
  display: flex;
  flex-direction: column;
`;

export const Layout = styled.div`
  display: flex;
  width: 100%;
`;

export const LineBox = styled.div`
  width: 100%;
  height: 1.25rem;
  border: 1px solid #ddd;
  border-right: none;
  margin-top: 0.65rem;
`;

export const LogoBox = styled.div`
  font-size: 2rem;
  font-weight: bold;
  margin-left: 1.5rem;
  margin-top: 0.3rem;
  color: #ff9999;
  margin-bottom: 1rem;
`;

export const ScrollableWrapper = styled.div`
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

export const FilterFrameBox = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
`;
