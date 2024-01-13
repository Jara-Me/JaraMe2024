package siliconDream.jaraMe.dto;

import java.awt.*;
import java.time.LocalDateTime;

public class MissionPostDTO {
    //작성자 알기 위해 userId 필요(프로필)
    Long userId;

    //어떤 미션인지 알기 위해서 groupId 필요
    Long groupId;

    //인증글 작성 시간
    LocalDateTime dateTime;

    //전체 공개 여부
    boolean display;

    //익명 여부
    boolean anonymous;

    //인증 내용
    String title;
    String textContent;
    String imageContent;

    //TODO:Getter and Setter
    public Long getUserId() {
        return userId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public boolean getDisplay() {
        return display;
    }

    public String getTitle() {
        return title;
    }

    public String getTextContent() {
        return textContent;
    }

    public String getImageContent() {
        return imageContent;
    }

    public boolean getAnonymous() {
        return anonymous;
    }



    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }


}
