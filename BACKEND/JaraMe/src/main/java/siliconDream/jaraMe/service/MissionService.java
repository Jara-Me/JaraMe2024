package siliconDream.jaraMe.service;

import org.springframework.stereotype.Service;
import siliconDream.jaraMe.dto.DailyMissionDTO;
import siliconDream.jaraMe.dto.MissionPostDTO;
import siliconDream.jaraMe.dto.GetMissionPostDTO;

import java.time.LocalDateTime;
import java.util.Optional;


public interface MissionService {
    Optional<GetMissionPostDTO> missionPost(MissionPostDTO missionPostDTO);
    Optional<GetMissionPostDTO>  getMissionPostDetails(Long missionPostId);
    boolean dailyMissionFinish(Long userId, Long groupId);
    DailyMissionDTO getDailyMission(Long userId, LocalDateTime todayDate);
    //boolean missionFinish(Long userId, Long groupId);


}
