package siliconDream.jaraMe.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class MissionPost {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column( nullable = false, unique = true)
    Long missionPostId;

    boolean anonymous;

    boolean display;

    String textTitle;

    String textContent;

    String imageContent;

    LocalDateTime postDateTime;

    //FK
    @ManyToOne
    @JoinColumn(name = "userId")
    User user;

    @ManyToOne
    @JoinColumn(name = "groupId")
    Group group;

    @OneToMany(mappedBy = "missionPost", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Comment> comment;

    @OneToMany(mappedBy = "missionPost", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Reaction> reaction;





    //TODO: getter and setter
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Group getGroup() {
        return group;
    }
    public void setGroup(Group group) {
        this.group = group;
    }

    public boolean getAnonymous() {
        return anonymous;
    }
    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }

    public boolean getDisplay() {
        return display;
    }
    public void setDisplay(boolean display) {
        this.display = display;
    }

    public String getTextTitle() {
        return textTitle;
    }
    public void setTextTitle(String textTitle) {
        this.textTitle = textTitle;
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

    public LocalDateTime getPostDateTime() {
        return postDateTime;
    }
    public void setPostDateTime(LocalDateTime postDateTime) {
        this.postDateTime = postDateTime;
    }

    public List<Comment> getComment() {
        return comment;
    }
    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

    public List<Reaction> getReaction() {
        return reaction;
    }
    public void setReaction(List<Reaction> reaction) {
        this.reaction = reaction;
    }

    public Long getMissionPostId() {
        return missionPostId;
    }
    public void setMissionPostId(Long missionPostId) {
        this.missionPostId = missionPostId;
    }

}
