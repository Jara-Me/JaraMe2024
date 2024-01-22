package siliconDream.jaraMe.domain;

import jakarta.persistence.*;

@Entity

@Table
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,unique = true)
    private Long reactionId;
    private String reactionType; //like, smile, good



    //FK
    @ManyToOne
    @JoinColumn(name="missionPost")
    private MissionPost missionPost;

    @ManyToOne
    @JoinColumn(name="user")
    private User user;


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
    public MissionPost getMissionPost() {
        return missionPost;
    }

    public void setMissionPost(MissionPost missionPost) {
        this.missionPost = missionPost;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
