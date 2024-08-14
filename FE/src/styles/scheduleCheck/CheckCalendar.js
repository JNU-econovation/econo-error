import styled from "styled-components";
import Modal from "react-modal";

export const ModifyIconWrapper = styled.div`
  margin-top: 0.22rem;
  margin-left: 0.4rem;
`;
export const HeaderContainer = styled.div`
  padding-top: 0.5rem;
  padding-left: 0.375rem;
  padding-right: 0.375rem;
  display: flex;
  flex-direction: row-reverse;
  outline: none;

  button {
    width: 3rem;
    background-color: #fff;
    border: none;
    svg {
      size: 5rem;
    }
  }
`;

export const DetailedInfoContainer = styled.div`
  padding-right: 1.75rem;
  padding-left: 1.75rem;
  padding-bottom: 1rem;
  p {
    margin-top: 0.6rem;
    margin-right: 0.6rem;
  }
`;

export const TitleBox = styled.div`
  display: flex;
  align-items: center;
  margin-left: 0.3rem;
  span {
    width: 1rem;
    height: 1rem;
    background-color: ${(props) => props.color || "#beb9ff"};
    margin-right: 1.6rem;
    border-radius: 0.3rem;
  }
  div {
    font-size: 1.6rem;
  }
`;

export const DateParagraph = styled.p`
  margin-left: 2.75rem;
  margin-bottom: 1.8rem;
`;
export const DetailIconWrapper = styled.div`
  margin-top: 1.2rem;
  margin-bottom: 1.2rem;

  display: flex;
  align-items: center;
  div {
    font-size: 1.1em;
  }
`;
export const ModalLayout = styled(Modal)`
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: white;
  z-index: 100;
  width: 37rem;
  height: auto;
  border-radius: 4px;
  box-shadow: 2px 2px 20px 2px rgba(0, 0, 0, 0.3);
  outline: none;
`;
