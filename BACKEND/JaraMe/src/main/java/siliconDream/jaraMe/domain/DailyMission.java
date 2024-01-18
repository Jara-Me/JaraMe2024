package siliconDream.jaraMe.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class DailyMission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dailyMissionId;

    private LocalDateTime dailyMissionDate;

    private boolean dailyMissionResult;

    //FK
    @ManyToOne
    @JoinColumn(name="userId")
    private User userId;

    @ManyToOne
    @JoinColumn(name="jaraUsId")
    private JaraUs jaraUsId;

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


    //마저 작성 필요
}
