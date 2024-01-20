package siliconDream.jaraMe.dto;

import java.awt.*;
import java.time.LocalDateTime;

public class MissionPostDTO {
    //작성자 알기 위해 userId 필요(프로필)
    Long userId;

    //어떤 미션인지 알기 위해서 jaraUsId 필요
    Long jaraUsId;

    //인증글 작성 시간
    LocalDateTime dateTime;

    //전체 공개 여부
    boolean display;

    //익명 여부
    boolean anonymous;

    //인증 내용
    String textTitle;
    String textContent;
    String imageContent;






    //TODO:Getter and Setter
    public Long getUserId() {
        return userId;
    }

    public Long getJaraUsId() {
        return jaraUsId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getTextTitle() {
        return textTitle;
    }

    public String getTextContent() {
        return textContent;
    }

    public String getImageContent() {
        return imageContent;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }


}
