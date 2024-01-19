package siliconDream.jaraMe.dto;

import org.springframework.cglib.core.Local;
import siliconDream.jaraMe.domain.JaraUs;
import siliconDream.jaraMe.domain.User;

import java.time.LocalDate;

public class DailyMissionRecordDTO {

    public LocalDate getMissionDate() {
        return missionDate;
    }

    public void setMissionDate(LocalDate missionDate) {
        this.missionDate = missionDate;
    }



    private LocalDate missionDate;
    private User user;

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

    private JaraUs jaraUs;

}
