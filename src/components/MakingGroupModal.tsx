import { useState } from "react";
import styled from "styled-components";

interface GroupModalProps {
  onClose: () => void;
}

function GroupModal({ onClose }: GroupModalProps) {
  const [groupName, setGroupName] = useState("");
  const [firsttime, setFirsttime] = useState(true);
  const [groupAvailable, setGroupAvailable] = useState(false);
  const [maxParticipants, setMaxParticipants] = useState(5);

  const handleCheckAvailability = () => {
    // 여기에서 중복 확인 로직을 추가하고 결과에 따라 setGroupAvailable 함수 호출
    // 예를 들어, 서버에서 중복 확인 후 결과를 받아와서 setGroupAvailable을 호출
    // setGroupAvailable(true); // 중복이 아니라면 true, 중복이면 false
    const isAvailable = groupName !== '솔룩스';
    setGroupAvailable(isAvailable);
    if (firsttime) setFirsttime(false);
  };

  const handleCreateGroup = () => {
    // 여기에서 Jara-Us 생성 로직을 추가하고,
    // 생성이 성공했을 때 onClose 함수와 alert를 실행
    // 생성에 실패했을 경우에는 alert 등을 추가로 처리할 수 있음
    // 예시로 생성이 항상 성공했다고 가정하고 alert를 추가함
    alert("Jara-Us가 생성되었습니다!");
    onClose();
  };

  const [searchQuery, setSearchQuery] = useState('');
  
    const handleSearch = () => {
      // 여기에 실제 검색 로직을 구현하세요.
      console.log(`Searching for: ${searchQuery}`);
    };

  return (
    <ModalOverlay>
      <ModalContainer>
        <CloseButton onClick={onClose}>&times;</CloseButton>
        <div className="title">Jara-Us 생성</div>
        <div className="input">
          <label>Jara-Us 명</label>
          <input
            type="text"
            value={groupName}
            placeholder="생설할 Jara-Us 이름을 입력해주세요 ..."
            onChange={(e) => setGroupName(e.target.value)}
          />
          <button className="check-button" onClick={handleCheckAvailability}>중복확인</button>
          {groupAvailable && (
            <small>사용 가능한 그룹명입니다</small>
          )}
          {!firsttime && !groupAvailable && (
            <small className="cannotuse">사용 불가능한 그룹명입니다</small>
          )}

        </div>
        <div className="input">
          <label>미션</label>
          <input type="text" className="mission" placeholder="미션 이름을 입력해주세요 ..."/>
        </div>
        <div className="input">
          <label>설명</label>
          <input type="text" className="explain" placeholder="미션에 대한 설명을 입력해주세요 ..."/>
        </div>
        <div className="input">
          <label>규칙</label>
          <input type="text" className="rule" placeholder="미션 규칙을 입력해주세요 ..."/>
        </div>
        <div className="input" id="maximum">
          <label>최대인원</label>
          <select
            value={maxParticipants}
            onChange={(e) => setMaxParticipants(Number(e.target.value))}
          >
            <option value={5}>5명</option>
            <option value={10}>10명</option>
            <option value={15}>15명</option>
          </select>
        </div>
        <div className="input">
          <label>분류</label>
          <input type="text" placeholder="분류를 입력하세요 ..." />
          <div className='search-button' onClick={handleSearch}>🔍︎</div>
        </div>
        <div className="input" id="disclose">
          <label>공개</label>
          <button>전체 공개</button>
          <button>초대된 사용자에게 공개</button>
          <button>비공개</button>
        </div>
        <div className="makecancel">
          <button id="make" onClick={handleCreateGroup}>생성</button>
          <button id="cancel">취소</button>
        </div>
        
      </ModalContainer>
    </ModalOverlay>
  );
}

const ModalOverlay = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background-color: rgba(0, 0, 0, 0.3);
  z-index: 999;
`;

const ModalContainer = styled.div`
  
  overflow-y: auto;
  width: 1000px;
  height: 700px;
  background-color: #fff;
  border-radius: 20px;
  padding: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);

  small{
    color: #4caf50;
    position: absolute;
    top: 140px;
    left: 260px;
    text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.1);
  }
  .cannotuse{
    color: red;
  }
  .title{
    font-size: 30px;
    margin-bottom: 32px;
  }

  .check-button {
    height: 30px;
    background-color: #4caf50;
    color: #fff;
    border: none;
    border-radius: 5px;
    margin-left: 30px;
    cursor: pointer;
  }

  .check-button:hover{
    background-color: green;
    color: lightgrey;
  }

  label{
    font-size: 28px;
    font-weight: bold;
    margin-right: 50px;
  }

  .input{
    text-align: left !important;
    margin-left: 55px;
    margin-bottom: 30px;
    
  }

  input{
    height: 30px;
    width: 350px;
    border-radius: 10px;
    border: 1px solid grey;
    padding-left: 10px;
  }

  #disclose button{
    font-size: 17px;
    font-weight: 600;
    margin-left: 25px;
    margin-right: 25px;
    width: 220px;
    height: 40px;
    background-color: white;
    border-radius: 9px;
    border: 1px solid lightgrey;
    cursor: pointer;

  }
  #disclose button:hover{
    background-color: grey;
    color: white;
  }

  #maximum{
    position: absolute;
    right: 120px;
  }

  #maximum label{
    width: 140px;
    position: absolute;
    top: 9px;
    right: 10px;
  }
  #maximum select{
    position: absolute;
    top: 14px;
    right: 10px;
  }

  select{
    height: 30px;
  }

  .makecancel button{
    width: 200px;
    height: 40px;
    border-radius: 15px;
    border-style: none;
    color: white;
    font-size: 20px;
    font-weight: bold;
    margin: 30px;
    cursor: pointer;
  }
  .makecancel #make{
    background-color: #669AFF;
  }
  .makecancel #make:hover{
    background-color: #557FD0;
  }
  .makecancel #cancel{
    background-color: #BDBDBD;
  }
  .makecancel #cancel:hover{
    background-color: #8E8E8E;
  }

  .mission{
    width: 760px;
  }
  .explain{
    width: 760px;
  }
  .rule{
    width: 760px;
    height: 90px;
  }

  .search-button{
    position: absolute;
    top: 449px;
    left: 515px;
  }
`;

const CloseButton = styled.span`
  font-size: 27px;
  position: absolute;
  top: 10px;
  right: 20px;
  cursor: pointer;

  &:hover {
    color: red;
  }
`;

export default GroupModal;
