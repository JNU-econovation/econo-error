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
    navigate("/");
  };

  return (
    <PageContainer>
      <ContentWrapper>
        <Header>
          <Title>프로필 선택</Title>
        </Header>
        <ProfilePictureContainer>
          <ProfilePicture
            src={
              selectedImage !== null
                ? `/${images[selectedImage]}`
                : "/Profile.png"
            }
            alt="Profile"
          />
        </ProfilePictureContainer>
        <MainContent>
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
            <CancelButton onClick={() => navigate("/")}>취소</CancelButton>
            <SaveButton onClick={handleSave}>저장</SaveButton>
          </ButtonContainer>
        </MainContent>
      </ContentWrapper>
    </PageContainer>
  );
};

export default ProfilePage;

const PageContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  padding: 2rem;
  box-sizing: border-box;
`;

const ContentWrapper = styled.div`
  width: 100%;
  max-width: 1200px;
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const Header = styled.div`
  width: 100%;
  text-align: left;
`;

const Title = styled.h1`
  font-size: 2rem;
`;

const ProfilePictureContainer = styled.div`
  display: flex;
  justify-content: center;
  width: 100%;
  margin-bottom: 1rem;
`;

const ProfilePicture = styled.img`
  width: 13rem;
  height: 13rem;
  border-radius: 50%;
`;

const MainContent = styled.div`
  width: 100%;
`;

const ImageShuffle = styled.div`
  display: flex;
  align-items: center;
  margin-bottom: 1rem;
`;

const RandomButton = styled.div`
  padding: 0 0.5rem;
  cursor: pointer;
`;

const ImageGrid = styled.div`
  display: grid;
  grid-template-columns: repeat(8, 1fr);
  grid-template-rows: repeat(2, 1fr);
  gap: 0.5rem;
  margin-bottom: 1rem;
`;

const ImageContainer = styled.div`
  aspect-ratio: 1;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  transition: all 0.3s ease;
  border: ${(props) =>
    props.selected ? "2px solid #007bff" : "2px solid transparent"};
  border-radius: 50%;

  &:hover {
    transform: scale(1.05);
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  }
`;

const Image = styled.img`
  max-width: 100%;
  max-height: 100%;
  object-fit: cover;
  /* border-radius: 6px; */
  border-radius: 50%;
`;

const ButtonContainer = styled.div`
  display: flex;
  justify-content: flex-end;
  gap: 0.8rem;
  margin-top: 0.5rem;
`;

const Button = styled.button`
  padding: 0.5rem 4rem;
  background-color: white;
  color: #333;
  border: 1px solid #cbcbcb;
  border-radius: 4px;
  cursor: pointer;
  transition: all 0.3s ease;

  &:hover {
    background-color: #007bff;
    color: white;
    border-color: #007bff;
  }
`;

const CancelButton = styled(Button)`
  &:hover {
    background-color: #dc3545;
    border-color: #dc3545;
  }
`;

const SaveButton = styled(Button)`
  &:hover {
    background-color: #28a745;
    border-color: #28a745;
  }
`;
