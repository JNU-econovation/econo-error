import { useState } from "react";
import styled from "styled-components";
import { FaRandom } from "react-icons/fa";

const ProfilePage = () => {
  const [selectedImage, setSelectedImage] = useState(null);
  const [images, setImages] = useState(
    Array(16).fill("https://via.placeholder.com/200")
  );

  const selectImage = (index) => {
    setSelectedImage(index);
  };

  return (
    <>
      <Header>
        <Title>프로필 선택</Title>
        <ProfilePicture
          src={
            selectedImage !== null
              ? images[selectedImage]
              : "https://via.placeholder.com/200"
          }
          alt="Profile"
        />
      </Header>
      <Container>
        <ImageShuffle>
          <FaRandom />
          <RandomButton>랜덤 이미지 생성</RandomButton>
        </ImageShuffle>
        <ImageGrid>
          {images.map((image, index) => (
            <ImageContainer
              key={index}
              onClick={() => selectImage(index)}
              selected={selectedImage === index}
            >
              {image && <Image src={image} alt={`option-${index}`} />}
            </ImageContainer>
          ))}
        </ImageGrid>
        <ButtonContainer>
          <CancleButton>취소</CancleButton>
          <SaveButton>저장</SaveButton>
        </ButtonContainer>
      </Container>
    </>
  );
};

export default ProfilePage;

const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 0 4rem;
`;

const Header = styled.div`
  display: flex;
  width: 52%;
  padding: 2rem 4rem 0 4rem;
  justify-content: space-between;
`;

const Title = styled.h1`
  font-size: 2rem;
  margin-right: 2rem; /* Space between title and profile picture */
`;

const ProfilePicture = styled.img`
  display: flex;
  justify-content: center;
  width: 13rem;
  height: 13rem;
  border-radius: 50%;
  margin-bottom: 1rem;
`;

const RandomButton = styled.div`
  padding: 0 0.5rem;
  cursor: pointer;
  align-self: flex-start;
`;

const ImageGrid = styled.div`
  display: grid;
  grid-template-columns: repeat(8, 1fr);
  gap: 0.5rem;
  margin-bottom: 1rem;
`;

const ImageContainer = styled.div`
  width: 9.8rem;
  height: 9.8rem;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
`;

const Image = styled.img`
  max-width: 100%;
  max-height: 100%;
`;

const SaveButton = styled.button`
  padding: 0.5rem 4rem;
  align-self: flex-end;
  background-color: white;
  border: 1px solid #cbcbcb;
  border-radius: 4px;
`;

const ImageShuffle = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 1rem;
  align-self: flex-start;
`;

const ButtonContainer = styled.div`
  display: flex;
  align-self: flex-end;
  gap: 0.8rem;
`;

const CancleButton = styled.button`
  padding: 0.5rem 4rem;
  background-color: white;
  border: 1px solid #cbcbcb;
  border-radius: 4px;
`;
