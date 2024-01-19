package siliconDream.jaraMe.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class DailyMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dailyMissionId;

    private LocalDateTime dailyMissionDate;

    private boolean dailyMissionResult;


    //FK
    @ManyToOne
    @JoinColumn(name="user")
    private User user;

    @ManyToOne
    @JoinColumn(name="jaraUs")
    private JaraUs jaraUs;

    //TODO: getter and setter
    public Long getDailyMissionId() {
        return dailyMissionId;
    }

    public void setDailyMissionId(Long dailyMissionId) {
        this.dailyMissionId = dailyMissionId;
    }

    public boolean isDailyMissionResult() {
        return dailyMissionResult;
    }

    public void setDailyMissionResult(boolean dailyMissionResult) {
        this.dailyMissionResult = dailyMissionResult;
    }
    public LocalDateTime getDailyMissionDate() {
        return dailyMissionDate;
    }

    public void setDailyMissionDate(LocalDateTime dailyMissionDate) {
        this.dailyMissionDate = dailyMissionDate;
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
