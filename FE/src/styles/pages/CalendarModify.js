import styled from "styled-components";

export const Layout = styled.div`
  width: 50rem;
`;
export const DetailInfoContainer = styled.div`
  margin-left: 4rem;
`;
export const TitleInput = styled.input`
  width: 100%;
  height: 2rem;
  margin-bottom: 2rem;
  margin-top: 2rem;
  margin-left: 0.7rem;
  font-size: 1.5rem;
  border: none;
  border-bottom: 1px solid #495057;
  outline: none;
`;
export const HeaderContainer = styled.div`
  display: flex;
  margin-left: 1rem;
`;
export const SaveBtn = styled.button`
  width: 4rem;
  height: 2rem;
  border-radius: 0.25rem;
  margin-top: 3rem;
  border: 0.5px solid #858585;
  outline: none;
  cursor: pointer;
  right: 0;
  background-color: ${(props) => (props.disabled ? "#e0e0e0" : "white")};
  color: ${(props) => (props.disabled ? "#9e9e9e" : "#3e3e3e")};
  border: ${(props) => (props.disabled ? "none" : "0.5px solid #858585")};

  &:disabled {
    cursor: default;
  }
`;

export const MemoWrapper = styled.div`
  .ql-editor {
    height: 110px;
    overflow-y: auto;
  }
  .ql-editor::before {
    font-style: normal !important;
    color: #999 !important;
  }
`;

export const PlaceInput = styled.input`
  border: none;
  width: 100%;
  outline: none;
  margin: 1.1rem 0;
`;

export const DateRowBox = styled.div`
  display: flex;
  align-items: center;
  gap: 0.7rem;
`;
