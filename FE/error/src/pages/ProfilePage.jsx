import { useState, useEffect } from "react";
import styled from "styled-components";
import { FaRandom } from "react-icons/fa";
import axios from "axios"; // axios를 import 합니다.

const ProfilePage = () => {
  const [selectedImage, setSelectedImage] = useState(null);
  const [images, setImages] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const storedToken = localStorage.getItem("slackToken");

  useEffect(() => {
    const fetchImages = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/images", {
          headers: { Authorization: `Bearer ${storedToken}` },
        });
        setImages(response.data);
        setIsLoading(false);
      } catch (error) {
        console.error("Failed to fetch images:", error);
        if (error.response) {
          // 서버 응답이 2xx 범위를 벗어난 상태 코드를 반환한 경우
          console.error(error.response.data);
          console.error(error.response.status);
          console.error(error.response.headers);
        } else if (error.request) {
          // 요청이 이루어졌으나 응답을 받지 못한 경우
          console.error(error.request);
        } else {
          // 요청을 설정하는 중에 오류가 발생한 경우
          console.error("Error", error.message);
        }
        setIsLoading(false);
      }
    };

    fetchImages();
  }, []);

  const selectImage = (index) => {
    setSelectedImage(index);
  };

  if (isLoading) {
    return <div>Loading...</div>;
  }

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
