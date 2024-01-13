package siliconDream.jaraMe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siliconDream.jaraMe.domain.MissionPost;
import siliconDream.jaraMe.dto.MissionPostDTO;
import siliconDream.jaraMe.dto.ReturnMissionPostDTO;
import siliconDream.jaraMe.repository.MissionRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public interface MissionService {
    boolean missionPost(MissionPostDTO missionPostDTO);
}
