package siliconDream.jaraMe.domain;

import jakarta.persistence.*;

@Entity

@Table
public class JoinUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long joinUsersId;

    @ManyToOne
    @JoinColumn(name="userId")
    private User userId;

    @ManyToOne
    @JoinColumn(name="jaraUsId")
    private JaraUs jaraUsId;

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

    public JaraUs getJaraUsId() {
        return jaraUsId;
    }

    public void setJaraUsId(JaraUs jaraUsId) {
        this.jaraUsId = jaraUsId;
    }
}


