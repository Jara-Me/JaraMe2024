package siliconDream.jaraMe.service;

import siliconDream.jaraMe.domain.MissionPost;
import siliconDream.jaraMe.dto.CommentDTO;
import siliconDream.jaraMe.dto.DailyMissionDTO;
import siliconDream.jaraMe.dto.MissionPostDTO;
import siliconDream.jaraMe.dto.GetMissionPostDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface MissionPostService {
    //미션 인증글 작성
    String missionPost(MissionPostDTO missionPostDTO, Long userId);

    //미션 인증글 조회
    GetMissionPostDTO getMissionPostDetails(Long missionPostId,Long userId);
    GetMissionPostDTO makeGetMissionPostDTO(Optional<MissionPost> missionPostOptional, Optional<String> reactionTypeOptional, Optional<List<CommentDTO>> commentOptional);


    String dailyMissionUpdate(Long userId,Long jaraUsId, Long missionPostId);
    void  dailyMissionFinish(Long dailyMissionId, MissionPost savedMissionPost, LocalDateTime postedDateTime);

    //Optional<DailyMissionDTO> getDailyMission(Long userId, LocalDateTime todayDate);
    int missionParticipationRate(Long userId, Long jaraUsId);

    String updateMissionPost(Long missionPostId, MissionPostDTO missionPostDTO, Long userId, LocalDate todayDate);


    }
