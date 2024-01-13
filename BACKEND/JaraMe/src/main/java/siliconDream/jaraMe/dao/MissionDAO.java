package siliconDream.jaraMe.dao;

import siliconDream.jaraMe.dto.ReturnMissionPostDTO;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.Optional;

public interface MissionDAO {
    public Optional<ReturnMissionPostDTO> saveMissionPost(Long userId, Long groupId, LocalDateTime dateTime, boolean display, boolean anonymous, String title, String textContent, String imageContent);
    public ReturnMissionPostDTO getMissionPost(Long missionPostId);
}
