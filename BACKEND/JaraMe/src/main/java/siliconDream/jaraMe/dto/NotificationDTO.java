package siliconDream.jaraMe.dto;

import siliconDream.jaraMe.domain.User;

import java.time.LocalDateTime;

public class NotificationDTO {
    private Long id;
    private Long userId;

    private String message;
    private LocalDateTime createdDateTime;
    private boolean isRead;
    public NotificationDTO(Long id, Long userId, String message, LocalDateTime createdDateTime, boolean read) {
        this.id = id;
        this.userId = userId;
        this.message = message;
        this.createdDateTime = createdDateTime;
        this.isRead = read;
    }


    //getters, and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
