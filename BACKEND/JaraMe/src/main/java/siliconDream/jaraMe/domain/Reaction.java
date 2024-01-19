package siliconDream.jaraMe.domain;

import jakarta.persistence.*;

@Entity

@Table
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,unique = true)
    private Long reactionId;
    private String reactionType;


    //FK
    @ManyToOne
    @JoinColumn(name="missionPostId")
    private MissionPost missionPostId;

    @ManyToOne
    @JoinColumn(name="userId")
    private User userId;


    //TODO: getter and setter
    public Long getReactionId() {
        return reactionId;
    }
    public void setReactionId(Long reactionId) {
        this.reactionId = reactionId;
    }

    public String getReactionType() {
        return reactionType;
    }
    public void setReactionType(String reactionType) {
        this.reactionType = reactionType;
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
