import { useState, useEffect } from "react";
import styled from "styled-components";
import { FaRandom } from "react-icons/fa";
import { useNavigate } from "react-router-dom";

const ProfilePage = () => {
  const [selectedImage, setSelectedImage] = useState(null);
  const navigate = useNavigate();
  const images = [
    "seed0011.png",
    "seed0014.png",
    "seed0065.png",
    "seed0141.png",
    "seed0154.png",
    "seed0182.png",
    "seed0256.png",
    "seed0291.png",
    "seed0298.png",
    "seed0365.png",
    "seed0419.png",
    "seed0460.png",
    "seed0301.png",
    "seed0461.png",
    "seed0877.png",
    "seed0882.png",
  ];

  useEffect(() => {
    const savedImage = localStorage.getItem("profileImage");
    if (savedImage) {
      setSelectedImage(images.indexOf(savedImage));
    }
  }, []);

  const selectImage = (index) => {
    setSelectedImage(index);
  };

  const handleSave = () => {
    if (selectedImage !== null) {
      localStorage.setItem("profileImage", images[selectedImage]);
    }
    navigate("/"); // MainPage로 이동
  };

  return (
    <>
      <Header>
        <Title>프로필 선택</Title>
        <ProfilePicture
          src={
            selectedImage !== null
              ? `/${images[selectedImage]}`
              : "/Profile.png"
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
              <Image src={`/${image}`} alt={`option-${index}`} />
            </ImageContainer>
          ))}
        </ImageGrid>
        <ButtonContainer>
          <CancleButton onClick={() => navigate("/")}>취소</CancleButton>
          <SaveButton onClick={handleSave}>저장</SaveButton>
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
  margin-top: 0.5rem;
`;

const CancleButton = styled.button`
  padding: 0.5rem 4rem;
  background-color: white;
  border: 1px solid #cbcbcb;
  border-radius: 4px;
`;
