package siliconDream.jaraMe.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

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

    @Column(name = "signUpDate")
    private LocalDate signUpDate;


    //TODO: getter and setter

    public LocalDate getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(LocalDate signUpDate) {
        this.signUpDate = signUpDate;
    }
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


