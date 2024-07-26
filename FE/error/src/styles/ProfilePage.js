import styled from "styled-components";

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 0 4rem;
`;

export const HeaderContainer = styled.div`
  display: flex;
  width: 52%;
  padding: 2rem 4rem 0 4rem;
  justify-content: space-between;
`;

export const TitleBox = styled.h1`
  font-size: 2rem;
  margin-right: 2rem;
`;

export const ProfilePicture = styled.img`
  display: flex;
  justify-content: center;
  width: 13rem;
  height: 13rem;
  border-radius: 50%;
  margin-bottom: 1rem;
`;

export const RandomButton = styled.div`
  padding: 0 0.5rem;
  cursor: pointer;
  align-self: flex-start;
`;

export const ImageGridItem = styled.div`
  display: grid;
  grid-template-columns: repeat(8, 1fr);
  gap: 0.5rem;
  margin-bottom: 1rem;
`;

export const ImageContainer = styled.div`
  width: 9.8rem;
  height: 9.8rem;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
`;

export const Image = styled.img`
  max-width: 100%;
  max-height: 100%;
`;

export const SaveButton = styled.button`
  padding: 0.5rem 4rem;
  align-self: flex-end;
  background-color: white;
  border: 1px solid #cbcbcb;
  border-radius: 4px;
`;

export const ImageShuffle = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 1rem;
  align-self: flex-start;
`;

export const ButtonContainer = styled.div`
  display: flex;
  align-self: flex-end;
  gap: 0.8rem;
  margin-top: 0.5rem;
`;

export const CancleButton = styled.button`
  padding: 0.5rem 4rem;
  background-color: white;
  border: 1px solid #cbcbcb;
  border-radius: 4px;
`;
