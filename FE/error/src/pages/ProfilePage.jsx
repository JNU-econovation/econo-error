import { useState, useEffect } from "react";
import { FaRandom } from "react-icons/fa";
import { useNavigate } from "react-router-dom";
import * as S from "../styles/ProfilePage.js";

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
      <S.HeaderContainer>
        <S.TitleBox>프로필 선택</S.TitleBox>
        <S.ProfilePicture
          src={
            selectedImage !== null
              ? `/${images[selectedImage]}`
              : "/Profile.png"
          }
          alt="Profile"
        />
      </S.HeaderContainer>
      <S.Container>
        <S.ImageShuffle>
          <FaRandom />
          <S.RandomButton>랜덤 이미지 생성</S.RandomButton>
        </S.ImageShuffle>
        <S.ImageGridItem>
          {images.map((image, index) => (
            <S.ImageContainer
              key={index}
              onClick={() => selectImage(index)}
              selected={selectedImage === index}
            >
              <S.Image src={`/${image}`} alt={`option-${index}`} />
            </S.ImageContainer>
          ))}
        </S.ImageGridItem>
        <S.ButtonContainer>
          <S.CancleButton onClick={() => navigate("/")}>취소</S.CancleButton>
          <S.SaveButton onClick={handleSave}>저장</S.SaveButton>
        </S.ButtonContainer>
      </S.Container>
    </>
  );
};

export default ProfilePage;
