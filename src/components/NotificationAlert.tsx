import React, { useState } from "react";
import styled, { keyframes } from "styled-components";

interface Notification {
  id: number;
  content: string;
}

interface NotificationAlertProps {
  notifications: Notification[];
  onClick: () => void;
}

const NotificationAlert: React.FC<NotificationAlertProps> = ({
  notifications,
  onClick,
}) => {
  const [isAlertVisible, setIsAlertVisible] = useState(false);

  const handleAlertClick = () => {
    setIsAlertVisible(!isAlertVisible);
  };

  return (
    <Container onClick={handleAlertClick}>
      <Icon>&#x1F514;</Icon>
      {isAlertVisible && (
        <AlertContainer>
          <AlertContent>
            {notifications.map((notification) => (
              <NotificationItem key={notification.id}>
                {notification.content}
              </NotificationItem>
            ))}
          </AlertContent>
        </AlertContainer>
      )}
    </Container>
  );
};

const Container = styled.div`
  position: fixed;
  top: 20px;
  right: 20px;
  background-color: #3498db;
  border-radius: 10px;
  padding: 10px;
  cursor: pointer;
`;

const Icon = styled.span`
  font-size: 20px;
`;
const fadeInOut = keyframes`from{opacity:0;}to{opacity:1;}`;
  
const AlertContainer = styled.div`
  position: fixed;
  top: 60px;
  right: 100px;
  width: 400px;
  height: 250px;
  overflow-y: auto;
  background-color: #fff;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
  border-radius: 5px;
  z-index: 100;
  animation: ${fadeInOut} 0.1s ease-in-out;
  &::-webkit-scrollbar {
    width: 5px;
  }

  &::-webkit-scrollbar-track {
    background: transparent;
  }

  &::-webkit-scrollbar-thumb {
    background-color: #888;
    border-radius: 4px;
  }
`;

const AlertContent = styled.div`
  padding: 10px;
`;

const NotificationItem = styled.div`
  padding: 8px;
  margin-bottom: 8px;
  border-radius: 5px;
  transition: background-color 0.3s;

  &:hover {
    background-color: #ccc;
  }
`;

export default NotificationAlert;
