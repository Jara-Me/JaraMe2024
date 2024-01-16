package siliconDream.jaraMe.domain;

import jakarta.persistence.*;

@Entity

@Table
public class JoinUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long joinUsersId;

    @JoinColumn(name="userId")
    private User userId;

    @JoinColumn(name="groupId")
    private Group groupId;


    //TODO: getter and setter

    public Long getJoinUsersId() {
        return joinUsersId;
    }

    public void setJoinUsersId(Long joinUsersId) {
        this.joinUsersId = joinUsersId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Group getGroupId() {
        return groupId;
    }

    public void setGroupId(Group groupId) {
        this.groupId = groupId;
    }
}


