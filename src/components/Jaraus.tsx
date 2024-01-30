import React, { useState } from 'react';
import styled from 'styled-components';
interface JarausContentProps {
  className?: string;
}
const JarausContent: React.FC<JarausContentProps> = ({ className }) => {
  return (
    <Jaraus>
      <div className='recommend-container'>
          <div className='recommend'>
            <div className='recommend-name'></div>
            <div className='recommend-explain'></div>
          </div>
          <div className='recommend'>
            <div className='recommend-name'></div>
            <div className='recommend-explain'></div>
          </div>
          <div className='recommend'>
            <div className='recommend-name'></div>
            <div className='recommend-explain'></div>
          </div>
        </div>
    </Jaraus>
  );
}

const Jaraus = styled.div`
box-shadow: 5px 0 10px rgba(0, 0, 0, 0.1), -5px 0 10px rgba(0, 0, 0, 0.1) ;
width: 1285px;
height: 280px;
position: absolute;
top:60px;
border-bottom-left-radius: 15px;
border-bottom-right-radius: 15px;
.recommend-container{
  margin-left: 60px;
  margin-top: 40px;
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    grid-column-gap: 70px;
  }
  .recommend{
    width: 300px;
    height: 200px;
    border-radius: 10px;
    background-color: lightgrey;
  }
  .recommend-name{
    width: 100px;
    height: 20px;
    border-radius: 10px;
    background-color: grey;
    margin: 50px 20px 20px 30px;
  }
  .recommend-explain{
    width: 100px;
    height: 20px;
    border-radius: 10px;
    background-color: grey;
    margin: 20px 20px 20px 30px;
  }
`;

export default JarausContent;
