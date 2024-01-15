package siliconDream.jaraMe.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DailyMission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dailyMissionId;

    public Long getDailyMissionId() {
        return dailyMissionId;
    }

    public void setDailyMissionId(Long dailyMissionId) {
        this.dailyMissionId = dailyMissionId;
    }

    public boolean isDailyMissionStatus() {
        return DailyMissionStatus;
    }

    public void setDailyMissionStatus(boolean dailyMissionStatus) {
        DailyMissionStatus = dailyMissionStatus;
    }

    private boolean DailyMissionStatus;

    //마저 작성 필요
}
