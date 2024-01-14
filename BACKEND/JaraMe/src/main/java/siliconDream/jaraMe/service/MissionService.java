package siliconDream.jaraMe.service;

import org.springframework.stereotype.Service;
import siliconDream.jaraMe.dto.MissionPostDTO;
import siliconDream.jaraMe.dto.GetMissionPostDTO;

import java.util.Optional;


public interface MissionService {
    Optional<GetMissionPostDTO> missionPost(MissionPostDTO missionPostDTO);
    Optional<GetMissionPostDTO>  getMissionPostDetails(Long missionPostId);

}
