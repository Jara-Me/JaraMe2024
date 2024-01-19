package siliconDream.jaraMe.domain;

import jakarta.persistence.*;

@Entity

@Table
public class JoinUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long joinUsersId;

    @ManyToOne
    @JoinColumn(name="user")
    private User user;

    @ManyToOne
    @JoinColumn(name="jaraUs")
    private JaraUs jaraUs;

    //TODO: getter and setter

    public Long getJoinUsersId() {
        return joinUsersId;
    }

    public void setJoinUsersId(Long joinUsersId) {
        this.joinUsersId = joinUsersId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public JaraUs getJaraUs() {
        return jaraUs;
    }

    public void setJaraUs(JaraUs jaraUs) {
        this.jaraUs = jaraUs;
    }

}


