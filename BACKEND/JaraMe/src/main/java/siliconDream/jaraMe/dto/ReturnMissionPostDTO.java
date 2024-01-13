package siliconDream.jaraMe.dto;

import siliconDream.jaraMe.domain.User;

import java.time.LocalDateTime;

public class ReturnMissionPostDTO {

    //TODO: userId로 USER테이블에서 얻어올 값들
    //유저 프로필이미지
    private String userProfileImage;
    //닉네임=>익명이면 익명으로 ?
    private String nickname;

    //TODO: missionPostId로 missionPost테이블에서 얻어올 값들
    //작성 일시
    private LocalDateTime dateTime;
    //인증글 제목
    private String title;

    //인증글 내용
    private String textContent;

    //인증글 이미지 파일 (선택)
    private String imageContent;

    //인증글 댓글(선택)
    //private Comment comment;

    //인증글 하트/따봉/웃음(선택)
    //private Reaction reaction;

    //TODO: 보류
    //미션인증글 id , 그룹 id 필요?



    //TODO:Getter and Setter

    public String getUserProfileImage() {
        return userProfileImage;
    }

    public void setUserProfileImage(String userProfileImage) {
        this.userProfileImage = userProfileImage;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getImageContent() {
        return imageContent;
    }

    public void setImageContent(String imageContent) {
        this.imageContent = imageContent;
    }


}

