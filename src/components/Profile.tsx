import React, { useState } from 'react';
import styled from 'styled-components';
interface ProfileContentProps {
    className?: string;
  }
const ProfileContent: React.FC<ProfileContentProps> = ({ className }) => {

    return(
        <Profiles>
            <div className='profile-container'>
                <div className='user-photo'></div>
                <div className='user-name'>솔룩스 님</div>
                <div className='user-point'>🪙200 포인트</div>
                <div className='user-page'>마이페이지</div>
                <div className='user-mission'>📖참여 중인 미션 2개</div>
            </div>
        </Profiles>
    )
}

const Profiles = styled.div`
width: 580px;
height: 200px;
box-shadow: 5px 0 10px rgba(0, 0, 0, 0.1), -5px 0 10px rgba(0, 0, 0, 0.1) ;
position: absolute;
top:60px;
border-bottom-left-radius: 15px;
border-bottom-right-radius: 15px;

.profile-container{
    width: 430px;
    height: 100px;
    margin: 0 auto;
    margin-top: 50px;
    display: grid;
    grid-template-columns: 1fr 1.7fr 1.7fr;
}
    .user-photo{
        grid-row: span 2;
        width: 100px;
        height: 100px;
        border-radius: 100px;
        background-color: grey;
    }
    .user-name{
        font-size: 25px;
        font-weight: 30px;
    }
    .user-page{
        color: grey;
    }
`;

export default ProfileContent;