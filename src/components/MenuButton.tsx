import React, { useState } from 'react';
import styled from 'styled-components';

const MenuButton: React.FC = () => {
  const [isMenuOpen, setIsMenuOpen] = useState(false);

  const handleButtonClick = () => {
    setIsMenuOpen((prev) => !prev);
  };

  return (
    <Wrapper>
      <Button onClick={handleButtonClick} isOpen={isMenuOpen}>
        메뉴
      </Button>
      <Menu isOpen={isMenuOpen}>
        <MenuItem>메뉴 항목 1</MenuItem>
        <MenuItem>메뉴 항목 2</MenuItem>
        <MenuItem>메뉴 항목 3</MenuItem>
      </Menu>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  position: relative;
  overflow: hidden;
`;

interface ButtonProps {
  isOpen: boolean;
}
const Button = styled.button<ButtonProps>`
  width: 40px;
  height: 100px;
  box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.05), -5px 5px 10px rgba(0, 0, 0, 0.2);
  border-top-right-radius: 10px;
  border-bottom-right-radius: 10px;
  background-color: #3498db;
  position: fixed;
  top: 30px;
  left: ${({ isOpen }) => (isOpen ? '250px' : '0px')};
  color: #fff;
  padding: 10px 15px;
  border: none;
  cursor: pointer;
  transition: left 0.3s ease-in-out;

  &:hover {
    transform: scale(1.1);
  }
`;

interface MenuProps {
  isOpen: boolean;
}

const Menu = styled.div<MenuProps>`
  position: fixed;
  top: 0;
  left: ${({ isOpen }) => (isOpen ? '0' : '-250px')};
  width: 250px;
  height: 100vh;
  background-color: #2c3e50;
  transition: left 0.3s ease-in-out;
  overflow: hidden;
  box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);

`;

const MenuItem = styled.div`
  padding: 15px;
  color: #fff;
  cursor: pointer;
  transition: background-color 0.3s ease-in-out;

  &:hover {
    background-color: #34495e;
  }
`;

export default MenuButton;
