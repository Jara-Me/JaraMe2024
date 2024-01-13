package siliconDream.jaraMe.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class MissionPost {


    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    public void setTextTitle(String textTitle) {
        this.textTitle = textTitle;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public void setImageContent(String imageContent) {
        this.imageContent = imageContent;
    }

    public void setPostDateTime(LocalDateTime postDateTime) {
        this.postDateTime = postDateTime;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    public boolean isDisplay() {
        return display;
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

    public LocalDateTime getPostDateTime() {
        return postDateTime;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="missionId",nullable = false,unique = true)
    Long id;

    Long userId;

    Long groupId;

    boolean anonymous;

    boolean display;

    String textTitle;

    String textContent;

    String imageContent;

    LocalDateTime postDateTime;
}
