import styled from "styled-components";
import { Title, Wrapper } from "../components/mypage-components";
import puppyProfile1 from "../assets/images/code1.png";
import puppyProfile2 from "../assets/images/code2.png";
import puppyProfile3 from "../assets/images/code3.png";
import puppyProfile4 from "../assets/images/code4.png";
import catProfile1 from "../assets/images/yoga1.jpg";
import catProfile2 from "../assets/images/yoga2.jpg";
import catProfile3 from "../assets/images/yoga3.jpg";
import catProfile4 from "../assets/images/yoga4.png";
import defaultImage1 from "../assets/images/sil1.jpeg";
import defaultImage2 from "../assets/images/sil2.png";
import defaultImage3 from "../assets/images/sil3.jpg";
import defaultImage4 from "../assets/images/sil4.jpeg";
import Dropdown from "../components/dropdown";
import ViewPostModal from "../components/view-post-modal";
import { useCallback, useState } from "react";

const Container = styled.div`
  width: 80%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  .history-container{
    margin-top: -500px;
  }
`;

interface GroupProps {
  groupname: string;
  content: string;
  image: string;
}

interface ItemProps {
  src: string;
  onClick: () => void;
}



const ItemImg: React.FC<ItemProps> = ({ src, onClick }) => (
  <Item src={src} onClick={onClick}></Item>
);


const ItemContainer = styled.div`
  width: 80%;
  max-width: 1200px;
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  grid-gap: 16px;
  justify-content: center;
  align-items: center;
  margin-top: 20px;
  margin-bottom: 20px;
`;

const Item = styled.img`
  width: 100%;
  height: 100%;
  object-fit: cover;
  cursor: pointer;
  border-radius: 8px;
  transition: transform 0.3s ease-in-out;

  &:hover {
    transform: scale(1.05);
  }
`;
export default function MyActivities() {
    const groups: { [groupname: string]: { content: string; image: string }[] } = {
      "C를 씹어먹자": [
        { content: "코딩 챌린지 내용1", image: puppyProfile1 },
        { content: "코딩 챌린지 내용2", image: puppyProfile2},
        { content: "코딩 챌린지 내용3", image: puppyProfile3 },
        { content: "코딩 챌린지 내용4", image: puppyProfile4 },
      ],
      "거북목탈퇴클럽": [
        { content: "거북목 탈출 챌린지 내용1", image: catProfile1 },
        { content: "거북목 탈출 챌린지 내용2", image: catProfile2 },
        { content: "거북목 탈출 챌린지 내용3", image: catProfile3 },
        { content: "거북목 탈출 챌린지 내용4", image: catProfile4 },
      ],
      "실리콘드림": [
        { content: "실리콘 밸리 챌린지 내용1", image: defaultImage1 },
        { content: "실리콘 밸리 챌린지 내용2", image: defaultImage2 },
        { content: "실리콘 밸리 챌린지 내용3", image: defaultImage3 },
        { content: "실리콘 밸리 챌린지 내용4", image: defaultImage4 },
      ],
      // Add more groups as needed
    };
  
    const [isOpenViewPostModal, setOpenViewPostModal] = useState<boolean>(false);
    const [selectedGroup, setSelectedGroup] = useState<string | null>(null);
    const [selectedGroupInfo, setSelectedGroupInfo] = useState<{ content: string; image: string } | null>(null);
  
    const onClickToggleViewPostModal = useCallback(() => {
      setOpenViewPostModal(!isOpenViewPostModal);
    }, [isOpenViewPostModal]);
  
    const onGroupSelect = (group: string | null) => {
      setSelectedGroup(group);
      const groupInfo = groups[group || ''] || null;
      setSelectedGroupInfo(groupInfo ? groupInfo[0] : null);
    };
  
    const filteredItems = selectedGroupInfo
      ? groups[selectedGroup || ''].map((item) => item.image)
      : Object.values(groups).flatMap((group) => group.map((item) => item.image));
  
    return (
      <>
        {isOpenViewPostModal && (
          <ViewPostModal onClickToggleModal={onClickToggleViewPostModal}></ViewPostModal>
        )}
  
        <Wrapper>
          <Title>참여 히스토리</Title>
          <Container className="history">
            <Dropdown onGroupSelect={onGroupSelect}></Dropdown>
            <ItemContainer>
              {filteredItems.map((item, index) => (
                <ItemImg key={index} src={item} onClick={onClickToggleViewPostModal}></ItemImg>
              ))}
            </ItemContainer>
            {selectedGroupInfo && (
              <div className="history-container">
                {/* <p>{selectedGroupInfo.content}</p> */}
                {/* <img src={selectedGroupInfo.image} alt={selectedGroupInfo.content} /> */}
              </div>
            )}
          </Container>
        </Wrapper>
      </>
    );
  }
