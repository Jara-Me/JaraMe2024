package siliconDream.jaraMe.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,unique = true)
    private Long scheduleId;


    private Long jaraUsId;
    private LocalDate scheduleDate;

    //TODO: getter and setter


    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Long getJaraUsId() {
        return jaraUsId;
    }

    public void setJaraUsId(Long jaraUsId) {
        this.jaraUsId = jaraUsId;
    }

    public LocalDate getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(LocalDate scheduleDate) {
        this.scheduleDate = scheduleDate;
    }
}
