import styled from "styled-components";
import { useState, useEffect } from "react";
import { HiPencil } from "react-icons/hi2";
import { useNavigate } from "react-router-dom";

const ProfileBar = () => {
  const [isHovered, setIsHovered] = useState(false);
  const [profileImage, setProfileImage] = useState("Profile.png");
  const navigate = useNavigate();

  useEffect(() => {
    const savedImage = localStorage.getItem("profileImage");
    if (savedImage) {
      setProfileImage(savedImage);
    }
  }, []);

  const handleProfileClick = () => {
    navigate("/profile");
  };

  return (
    <StyledProfileFrame>
      <ProfileImageContainer
        onMouseEnter={() => setIsHovered(true)}
        onMouseLeave={() => setIsHovered(false)}
        onClick={handleProfileClick}
      >
        <ProfileImage src={`/${profileImage}`} alt="Profile" />
        {/* <ProfileImage src={`/Profile.png`} alt="Profile" /> */}
        {isHovered && (
          <EditIconOverlay>
            <HiPencil />
          </EditIconOverlay>
        )}
      </ProfileImageContainer>
    </StyledProfileFrame>
  );
};

export default ProfileBar;

const StyledProfileFrame = styled.div`
  width: 15.625rem;
  height: 15rem;
  align-items: center;
  display: flex;
  flex-direction: column;
  margin-bottom: 0.6rem;
`;

const ProfileImageContainer = styled.div`
  position: relative;
  cursor: pointer;
`;

const ProfileImage = styled.img`
  margin-top: 1.25rem;
  width: 13rem;
  height: 13rem;
`;

const EditIconOverlay = styled.div`
  position: absolute;
  top: 1.5rem;
  left: 0;
  right: 0;
  bottom: 0;
  border-radius: 50%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  color: white;
  font-size: 2rem;
`;
