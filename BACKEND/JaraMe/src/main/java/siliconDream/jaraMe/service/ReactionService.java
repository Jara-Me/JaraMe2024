package siliconDream.jaraMe.service;

import siliconDream.jaraMe.dto.MissionReactionDTO;

public interface ReactionService {

    String addReaction(MissionReactionDTO missionReactionDTO, Long userId);
    String deleteReaction(MissionReactionDTO missionReactionDTO, Long userId);
}
