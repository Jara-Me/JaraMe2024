import React from 'react';
import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';


const MyJaraus: React.FC = () => {
  let navigate = useNavigate();
  return (
    <MyJarausWrapper>
      <div className='my-Jaraus'>
        
        
        <div className='container'>
          <div className='backbutton' onClick={()=>navigate('/')}>{'<'}</div>
          <div className='title-container'>
            <div className='title' onClick={()=>navigate('.')}>내가 가입한 Jara-Us</div>
          </div>

          <div className='my-container'>

            <div className='recommend'>
              <div className='recommend-photo'></div>
              <div className='recommend-name'></div>
              <div className='recommend-explain'></div>
            </div>
            <div className='recommend'>
              <div className='recommend-photo'></div>
              <div className='recommend-name'></div>
              <div className='recommend-explain'></div>
            </div>
            <div className='recommend'>
              <div className='recommend-photo'></div>
              <div className='recommend-name'></div>
              <div className='recommend-explain'></div>
            </div>
            <div className='recommend'>
              <div className='recommend-photo'></div>
              <div className='recommend-name'></div>
              <div className='recommend-explain'></div>
            </div>

          </div>
          
        </div>

      </div>
    </MyJarausWrapper>
  );
};
const MyJarausWrapper = styled.div`
  display: flex;
  flex-direction: column;

  .backbutton{
    text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.2);
    position: absolute;
    top: 30px;
    left: 40px;
    font-size: 30px;
    font-weight: 600;
    cursor: pointer;
  }
  .backbutton:hover{
    color: grey;
  }
  .container {
    display: grid;
    grid-template-columns: 1fr 2fr;
  }

  .title-container {
    overflow: hidden;
    height: 100vh;
    font-size: 25px;
    border-right: 1.5px solid lightgrey;
    padding-right: 10px;
    text-align: center;
    flex: 0; /* flex-grow: 0; */
  }

  .title {
    margin-top: 380px;
    font-weight: 570;
    cursor: pointer;
  }

  .my-container {
    &::-webkit-scrollbar {
      width: 10px;
    }
  
    &::-webkit-scrollbar-track {
      background: transparent;
    }
  
    &::-webkit-scrollbar-thumb {
      background-color: #888;
      border-radius: 4px;
    }
    height:100vh;
    overflow-y: auto; /* 세로 스크롤을 추가하여 my-container에서만 스크롤이 가능하도록 설정 */
  }

  .recommend:hover {
      background-color: lightgrey;
      transition: background-color 0.3s ease;
  }
    .recommend {
      border-bottom: 1px solid lightgrey;
      cursor: pointer;
      margin: 0 auto;
      display: grid;
      grid-template-columns: 1fr 1fr;
      width: 1049px;
      height: 280px;
      background-color: white;
    }
    .recommend-photo{
        grid-row: span 2;
        margin-left: 50px;
        margin-top: 50px;
        width: 160px;
        height: 180px;
        border-radius: 15%;
        background-color: grey;
    }
    .recommend-name {
        position: relative;
        width: 200px;
        height: 30px;
        border-radius: 10px;
        background-color: grey;
        margin-left: 50px;
        margin-top: 45px;
    }

    .recommend-explain {
        position: relative;
        width: 300px;
        height: 90px;
        border-radius: 10px;
        background-color: grey;
        margin-left: 50px;
    }
    
  
`;
export default MyJaraus;
