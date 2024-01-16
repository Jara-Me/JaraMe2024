package siliconDream.jaraMe.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity

@Table
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long commentId;

    private String commentContent;

    private LocalDateTime commentDate;

    // FK
    @ManyToOne
    @JoinColumn(name = "mission_postId")
    private MissionPost missionPostId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User userId;


    //TODO: getter and setter

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public LocalDateTime getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDateTime commentDate) {
        this.commentDate = commentDate;
    }

    public MissionPost getMissionPostId() {
        return missionPostId;
    }

    public void setMissionPostId(MissionPost missionPostId) {
        this.missionPostId = missionPostId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }


}