import React, { useState, useRef, useEffect } from 'react';
import styled from 'styled-components';
import { Routes, Route, BrowserRouter, Link, Router, useNavigate } from "react-router-dom";
import axios from 'axios';

import Mong from '../components/mong';
import SearchContent from '../components/Search';
import ProfileContent from '../components/Profile';
import MenuButton from '../components/MenuButton';
import TodayContent from '../components/Today';
import JarausContent from '../components/Jaraus';
import Calendar from '../components/Calender';
import MyJaraus from './MyJaraus';
import NotificationAlert from '../components/NotificationAlert';
import GroupModal from '../components/MakingGroupModal';

interface BoxStyle {
  id: string;
  left: number;
  top: number;
  width: number;
  height: number;
  isOpen: boolean;
  content: string;
}
interface SearchContentProps {
  className: string;
}

function Main() {
  const [mongOpen,setMongOpen] = useState(false);
  const elementRef = useRef<HTMLDivElement>(null);

  const MongOpen = () => {
    setMongOpen((prevOpen) => !prevOpen);
  };
  
  const isPlus = (id: string): boolean => id === 'searching' || id === 'jaraus';

  useEffect(() => {
    if (elementRef.current) {
      elementRef.current.style.top = mongOpen ? '800px' : 'initial';
      elementRef.current.style.transition= 'top 0.3s ease-in-out';
    }
  }, [mongOpen]);


  const [boxStyles, setBoxStyles] = useState<BoxStyle[]>([
    { id: 'searching', left: 200, top: 100, width: 580, height: 60, isOpen: true, content: '미션 탐색' },
    { id: 'profile', left: 900, top: 100, width: 580, height: 60, isOpen: true, content: '프로필' },
    { id: 'today', left: 200, top: 530, width: 580, height: 60, isOpen: true, content: '오늘의 미션' },
    { id: 'callender', left: 900, top: 400, width: 580, height: 60, isOpen: true, content: '캘린더' },
    { id: 'jaraus', left: 200, top: 960, width: 1285, height: 60, isOpen: true, content: 'Jara-Us' },
  ]);

  const [yellowContents, setYellowContents] = useState<{ [key: string]: (props: { className?: string }) => JSX.Element }>({
    searching: ({ className }) => <SearchContent className={`yellow-box ${className}`} />,
    profile: ({ className }) => <ProfileContent className={`yellow-box ${className}`} />,
    callender: ({ className }) => <Calendar className={`yellow-box ${className}`} />,
    today: ({ className }) => <TodayContent className={`yellow-box ${className}`} />,
    jaraus: ({ className }) => <JarausContent className={`yellow-box ${className}`} />,
  });
  

  

  const toggleYellowBox = (id: string) => {
    setBoxStyles((prevStyles) =>
      prevStyles.map((style) =>
        style.id === id
          ? {
              ...style,
              isOpen: !style.isOpen,
            }
          : style
      )
    );
    
  };

  const [isGroupModalOpen, setGroupModalOpen] = useState(false);
  const openModal = (id: string) => {
    if (id === 'jaraus') {
      setGroupModalOpen((prevOpen) => !prevOpen);
    }
  };

  let navigate = useNavigate();
  const openDetail = (id: string) => {
    if (id==='jaraus'){
      navigate('./my');
    }
    else if (id === 'searching'){
      navigate('./sd');
    }
  }
  
  // 알림창
  const notifications = [
    { id: 1, content: "알림 1" },
    { id: 2, content: "알림 2" },
    { id: 3, content: "알림 3" },
    { id: 4, content: "알림 4" },
    { id: 5, content: "알림 5" },
    { id: 6, content: "알림 6" },
    { id: 7, content: "알림 7" },
    // ... 다른 알림들
  ];

  // 알림 아이콘 클릭 시 실행될 함수
  const handleAlertClick = () => {
    // 원하는 동작을 수행
    console.log("알림 아이콘 클릭!");
  };

  return (
    <Mains isOpen={isGroupModalOpen}>
      <div className="App">
        {/* <div className='mong-button' ref={elementRef} onClick={MongOpen}></div> */}
        
        
        {boxStyles.map(({ id, left, top, width, height, isOpen, content }) => (
          <div
            
            key={id}
            className={`box ${isOpen ? id + '-yellow' : ''}`}
            id={id}
            style={{
              left,
              top,
              width,
              height,
              boxShadow: isOpen ? '5px 0 10px rgba(0, 0, 0, 0.1), -5px 0 10px rgba(0, 0, 0, 0.1)' : '',
              borderBottomLeftRadius: isOpen ? 0 : '15px',
              borderBottomRightRadius: isOpen ? 0 : '15px',
            }}
            
          >
            <div style={{position:'absolute', left:'50px'}} onClick={() => openDetail(id)}>{content}</div>
            

            {isOpen && yellowContents[id]({ className: id + '-yellow' })}
    
            {isPlus(id) && <div className= {id + 'plus-button'} onClick={() => openModal(id)}>+</div>}
            {(id === 'jaraus') && isGroupModalOpen && <GroupModal onClose={() => openModal(id)}/>}

            <div className='open-button' onClick={() => toggleYellowBox(id)}>{isOpen? '▲':'▼'}</div>
          </div>
        ))}
        <div style={{ position: 'absolute', top: '1380px', width: '30px', height: '30px' }}></div>
      </div>
      
      <MenuButton/>
      <NotificationAlert
        notifications={notifications}
        onClick={handleAlertClick}
      />
      
      <Mong />

    </Mains>
  );
}
interface MainProps {
  isOpen: boolean;
}
const Mains = styled.div<MainProps>`
.searchingplus-button{
  font-size: 28px;
  position: absolute;
  right: 75px;
  top: 10px;
}
.jarausplus-button{
  font-size: 28px;
  position: absolute;
  right: 75px;
  top: 10px;
}
.open-button{
  position:absolute;
  right: 30px;
}
  .mong-button{
    cursor: pointer;
    position: absolute;
    text-align: center;
    background-color: grey;
    width: 175px;
    height: 40px;
    left: 750px;
    box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.05), -5px 5px 10px rgba(0, 0, 0, 0.2);
    border-bottom-left-radius: 10px;
    border-bottom-right-radius: 10px;
    z-index: 2;
  }

  .box {
    position: absolute;
    text-align: center;
    background-color: lightgrey;
    box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.2), -5px 5px 10px rgba(0, 0, 0, 0.2);
    display: flex;
    align-items: center;
    justify-content: center;
    cursor: pointer;
    border-radius: 15px;
  }

  .yellow-box {
    pointer-events: none;
    position: absolute;
    top: ${({ isOpen }) => (isOpen ? 'calc(100% - 300px)' : '100%')};
    left: 0;
    width: 100%;
    height: 300px;
    box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.1), -5px 5px 10px rgba(0, 0, 0, 0.1);
    display: flex;
    justify-content: center;
    align-items: center;
    border-bottom-left-radius: 15px;
    border-bottom-right-radius: 15px;
    transition: top 0.3s ease-in-out;
  }

`;

export default Main;
