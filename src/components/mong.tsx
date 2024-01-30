import React, { useState, useRef } from 'react';
import styled, { keyframes } from 'styled-components';
import MongStore from './MongStore';

function Mong() {
  const [isMenuOpen, setIsMenuOpen] = useState(false);
  const [showInfoTooltip, setShowInfoTooltip] = useState(false);
  const [isStoreOpen, setIsStoreOpen] = useState(false);

  const characterRef = useRef<any>(null);

  const handleStoreClick = () => {
    if (characterRef.current) {
      characterRef.current.style.transition = 'left 0.3s ease-in-out';
    }
    setIsStoreOpen((prev) => !prev);
  };
  const handleButtonClick = () => {
    setIsMenuOpen((prev) => !prev);
  };
  const handleInfoHover = () => {
    setShowInfoTooltip(true);
  };

  const handleInfoLeave = () => {
    setShowInfoTooltip(false);
  };

  const [heartList, setHeartList] = useState([] as { id: number; position: { top: number; left: number } }[]);

  const getRandomPosition = () => {
    const characterElement = characterRef.current;

  if (characterElement) {
    const rect = characterElement.getBoundingClientRect();
    const randomX = Math.floor(Math.random() * 60) - 30;
    const randomY = Math.floor(Math.random() * 60) - 30;

    return { left: rect.left + randomX, top: rect.top + randomY };
  }
  //characterRef가 null이면 기본 위치 반환
  return { left: 1000, top: 250 };
  };

  const handleCharacterClick = () => {
    const heartId = Date.now();
    setHeartList((prevList) => [...prevList, { id: heartId, position: getRandomPosition() }]);

    setTimeout(() => {
      setHeartList((prevList) => prevList.filter((heart) => heart.id !== heartId));
    }, 990);
  };

  return (
    <Wrapper>
      <Button onClick={handleButtonClick} isOpen={isMenuOpen}>
        
      </Button>
      <Mongs isOpen={isMenuOpen}>
      <div className='mong-container'>
        <div className='mong-room'>
          
          <div
            className='info'
            onMouseEnter={handleInfoHover}
            onMouseLeave={handleInfoLeave}
          >
            ℹ️
            {showInfoTooltip && (
              <Tooltip>
                <div>
                  성장몽을 클릭해서 호감도를 쌓아보세요!
                  미션 완료 후 받은 포인트로 미션 패스권을 구매할 수 있습니다.
                </div>
              </Tooltip>
            )}
          </div>
          <div className='character' onClick={handleCharacterClick} ref={characterRef}
          style={{ left: isStoreOpen ? '345px' : '635px' }}></div>
          {heartList.map(({ id, position }) => (
            <div key={id} className='heart' style={position}>
              ❤️
            </div>
          ))}
          <div className='mong-room-floor'></div>
        </div>
      </div>
      <MongStore isOpen={isMenuOpen} onStoreClick={handleStoreClick} characterRef={characterRef}/>
      </Mongs>
    </Wrapper>
    
  );
}
const Wrapper = styled.div`
  position: relative;
  overflow: hidden;
`;
const fadeInOut = keyframes`from{opacity:0;}to{opacity:1;}`;
const Tooltip = styled.div`
  width: 400px;
  height: 100px;
  font-size: 18px;
  display: flex;
  align-items: center;
  justify-content: center;
  position: absolute;
  top: 2px;
  left: 70px;
  background-color: white;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  animation: ${fadeInOut} 0.1s ease-in-out;
`;
interface ButtonProps {
  isOpen: boolean;
}
const Button = styled.button<ButtonProps>`
  background-color: grey;
  width: 175px;
  height: 40px;
  box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.05), -5px 5px 10px rgba(0, 0, 0, 0.2);
  border-bottom-left-radius: 10px;
  border-bottom-right-radius: 10px;
  position: fixed;
  top: ${({ isOpen }) => (isOpen ? '800px' : '0px')};
  left: 750px;
  color: #fff;
  padding: 10px 15px;
  border: none;
  cursor: pointer;
  transition: top 0.3s ease-in-out;
  background-image: ${({ isOpen }) => (isOpen ? '' : 'url("/mong.png")')};
  background-size: cover; 
  background-position: center; 

  &:hover {
    transform: scale(1.1);
  }
`;
interface MongProps {
  isOpen: boolean;
}
const Mongs = styled.div<MongProps>`
  position: fixed;
  top: ${({ isOpen }) => (isOpen ? '0' : '-800px')};
  left: 0;
  transition: top 0.3s ease-in-out;
  .mong-container {
    width: 100vw;
    height: 500px;
    position: absolute;
    text-align: center;
    background-color: rgb(0, 0, 0, 0.9);
    display: flex;
    align-items: center;
    justify-content: center;
    cursor: pointer;
  }
  .mong-room {
    width: 100%;
    height: 500px;
    margin: 0 auto;
    background-color: #bec0c1;
  }
  .mong-room-floor {
    width: 100%;
    height: 310px;
    margin-top: 490px;
    background-color: #4a3e13;
  }
  .info {
    position: absolute;
    top: 130px;
    left: 55px;
    width: 50px;
    height: 50px;
    border-radius: 50px;
    font-size: 50px;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  .character {
    background-image: url('/mong.png'); 
    background-size: cover; 
    background-position: center; 
    width: 430px;
    height: 400px;
    position: absolute;
    top: 250px;
  }
  .heart {
    font-size: 50px;
    position: absolute;
    animation: heartAnimation 1s ease-out;
  }

  @keyframes heartAnimation {
    0% {
      transform: translateY(0);
    }
    100% {
      transform: translateY(-50px);
    }
  }
  
`;

export default Mong;
