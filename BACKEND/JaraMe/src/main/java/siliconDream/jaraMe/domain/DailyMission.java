package siliconDream.jaraMe.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class DailyMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dailyMissionId;

    @Column(columnDefinition = "boolean default false") //기본값 false로 설정
    private boolean dailyMissionResult;


    @Column(nullable = true)
    private LocalDateTime doneDateTime;
    private LocalDate scheduleDate; //인증예정일 날짜 (오늘날짜의 데일리미션이 맞는지 확인하기 위함)


    @OneToOne //확인 예정
    @JoinColumn(name="missionPost", insertable = true, updatable = true) //updatable = false?
    private MissionPost missionPost;

    //FK
    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "jaraUs")
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

    public LocalDateTime getDoneDateTime() {
        return doneDateTime;
    }

    public void setDoneDateTime(LocalDateTime doneDateTime) {
        this.doneDateTime = doneDateTime;
    }

    public LocalDate getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(LocalDate scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public MissionPost getMissionPost() {
        return missionPost;
    }

    public void setMissionPost(MissionPost missionPost) {
        this.missionPost = missionPost;
    }
}
