package siliconDream.jaraMe.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;
import siliconDream.jaraMe.domain.JaraUs;
import siliconDream.jaraMe.domain.MissionPost;
import siliconDream.jaraMe.domain.User;

import java.time.LocalDate;
@Getter
@Setter
public class DailyMissionRecordDTO {



    private LocalDate missionDate;
    private User user;
    private MissionPost missionPost;
    private boolean missionResult;


    private JaraUs jaraUs;

}
