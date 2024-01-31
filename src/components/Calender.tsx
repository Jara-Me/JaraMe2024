import React, { useState, useEffect } from 'react';
import styled from 'styled-components';
import axios from 'axios';

interface PointMission {
  changeAmount: number;
  plusOrMinus: boolean;
  task: string;
}

interface MissionHistory {
  missionDate: string;
  jaraUsId: number;
  jaraUsName: string;
  missionName: string;
  missionPostId: number | null;
  missionResult: boolean;
}

interface Mission {
  dailyMissionResult: boolean;
  jaraUsName: string;
  missionName: string;
}

interface CalendarProps {
  className?: string;
}

const Calendar: React.FC<CalendarProps> = ({ className }) => {
  const daysInMonth = 31;
  const startDay = 3;

  const [selectedDate, setSelectedDate] = useState<number | null>(null);
  const [pointMissionData, setPointMissionData] = useState<PointMission[]>([]);
  const [missionHistoryData, setMissionHistoryData] = useState<MissionHistory[]>([]);
  const [missionData, setMissionData] = useState<Mission[]>([]);

  const handleDateClick = async (day: number) => {
    setSelectedDate(day);
    try {
      const formattedDate = `2024-01-${day}`;
      const response = await axios.get('/api/notice/calendar', {
        params: {
          selectedDate: formattedDate,
        },
      });
      setMissionHistoryData(response.data.calendarMissionHistoryDTOs);
      setPointMissionData(response.data.calendarPointDTOs);
    } catch (error) {
      console.error('Error fetching mission data:', error);
    }
  };

  const toggleMissionStatus = (index: number) => {
    setMissionData((prevData) => {
      const newData = [...prevData];
      newData[index].dailyMissionResult = !newData[index].dailyMissionResult;
      return newData;
    });
  };

  const days = [];
  for (let i = 0; i < startDay; i++) {
    days.push(<EmptyDay key={`empty-${i}`} />);
  }

  for (let i = 1; i <= daysInMonth; i++) {
    days.push(
      <Day
        key={i}
        isSelected={selectedDate === i}
        onClick={() => handleDateClick(i)}
      >
        {i}
      </Day>
    );
  }

  const selectedMissionData = selectedDate !== null ? missionData : [];
  const missionList = selectedMissionData.map((mission, index) => (
    <MissionItem
      key={index}
      completed={mission.dailyMissionResult}
      onClick={() => toggleMissionStatus(index)}
    >
      <input type="checkbox" checked={mission.dailyMissionResult} readOnly />
      {mission.jaraUsName} - {mission.missionName}
    </MissionItem>
  ));

  ///////////////////////////////////////////////////
  useEffect(() => {
  const fetchMissionData = async () => {
    try {
      const formattedDate = selectedDate ? `2024-01-${selectedDate}` : '';

      const response = await axios.get('/api/notice/calendar', {
        params: {
          selectedDate: formattedDate,
        },
      });
      setMissionData(response.data.calendarMissionHistoryDTOs);
    } catch (error) {
      console.error('Error fetching mission data:', error);
      // 에러 시 기본
      setMissionData([
        { dailyMissionResult: true, jaraUsName: '자라어스1 이름', missionName: '자라어스1 미션이름' },
        { dailyMissionResult: false, jaraUsName: '자라어스2 이름', missionName: '자라어스2 미션이름' },
        { dailyMissionResult: true, jaraUsName: '자라어스3 이름', missionName: '자라어스3 미션이름' },
        { dailyMissionResult: true, jaraUsName: '자라어스4 이름', missionName: '자라어스4 미션이름' },
      ]);
    }
  };
  fetchMissionData();
  }, [selectedDate]);
  ///////////////////////////////////////////////////

  return (
    <CalendarWrapper>
      <DayNames>
        <div>Sun</div>
        <div>Mon</div>
        <div>Tue</div>
        <div>Wed</div>
        <div>Thu</div>
        <div>Fri</div>
        <div>Sat</div>
      </DayNames>
      <Days>{days}</Days>
      {selectedDate !== null && (
        <MissionListWrapper>
          <div>Mission List for {selectedDate}</div>
          {missionList}
        </MissionListWrapper>
      )}
    </CalendarWrapper>
  );
};
const CalendarWrapper = styled.div`
  box-shadow: 5px 0 10px rgba(0, 0, 0, 0.1), -5px 0 10px rgba(0, 0, 0, 0.1);
  width: 580px;
  height: 460px;
  position: absolute;
  top: 60px;
  border-bottom-left-radius: 15px;
  border-bottom-right-radius: 15px;
  font-family: 'Arial', sans-serif;
`;

const DayNames = styled.div`
  width: 350px;
  display: grid;
  grid-template-columns: repeat(7, 1fr);
  font-weight: bold;
  text-align: center;
  margin: 0 auto;
  margin-top: 15px;
`;

const Days = styled.div`
  width: 350px;
  display: grid;
  grid-template-columns: repeat(7, 1fr);
  margin: 0 auto;
  margin-top: 15px;
  padding-bottom: 30px;
  border-bottom: 1px solid grey;
`;

const Day = styled.div<{ isSelected: boolean }>`
  display: flex;
  align-items: center;
  justify-content: center;
  height: 40px;
  border: 1px solid #ccc;
  cursor: pointer;
  background-color: ${({ isSelected }) => (isSelected ? '#4caf50' : 'white')};
  color: ${({ isSelected }) => (isSelected ? 'white' : 'black')};

  &:hover {
    background-color: #f0f0f0;
  }
`;

const MissionListWrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 20px;
`;

const MissionItem = styled.div<{ completed: boolean }>`
  display: flex; // 체크박스와 텍스트를 가로로 나란히 배치
  align-items: center;
  margin-top: 5px;
  cursor: pointer; // 클릭 가능하도록 커서 추가

  input[type='checkbox'] {
    margin-right: 8px; // 체크박스 오른쪽 여백 추가
  }

  text-decoration: ${({ completed }) => (completed ? 'line-through' : 'none')};
`;
const EmptyDay = styled.div`
  height: 40px;
  border: 1px solid #ccc;
`;

export default Calendar;