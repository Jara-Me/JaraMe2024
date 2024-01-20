package siliconDream.jaraMe.service;

import siliconDream.jaraMe.dto.DailyMissionDTO;
import siliconDream.jaraMe.dto.MissionPostDTO;
import siliconDream.jaraMe.dto.GetMissionPostDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;


public interface MissionPostService {
    Optional<GetMissionPostDTO> missionPost(MissionPostDTO missionPostDTO);
    Optional<GetMissionPostDTO>  getMissionPostDetails(Long missionPostId);
    boolean dailyMissionFinish(Long userId, Long jaraUsId);
    //Optional<DailyMissionDTO> getDailyMission(Long userId, LocalDateTime todayDate);
    int missionParticipationRate(Long userId,Long jaraUsId);
    String updateMissionPost(Long missionPostId,MissionPostDTO missionPostDTO, Long userId, LocalDate todayDate);



}
