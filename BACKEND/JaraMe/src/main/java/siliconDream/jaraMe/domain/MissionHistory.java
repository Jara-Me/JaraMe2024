package siliconDream.jaraMe.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class MissionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,unique = true)
    private Long missionHistoryId;

    private LocalDate missionDate;

    //fk
    @JoinColumn(name="user")
    @ManyToOne
    private User user;

    @JoinColumn(name="jaraUs")
    @ManyToOne
    private JaraUs jaraUs;

    //TODO: getter and setter


    public Long getMissionHistoryId() {
        return missionHistoryId;
    }

    public void setMissionHistoryId(Long missionHistoryId) {
        this.missionHistoryId = missionHistoryId;
    }

    public LocalDate getMissionDate() {
        return missionDate;
    }

    public void setMissionDate(LocalDate missionDate) {
        this.missionDate = missionDate;
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


