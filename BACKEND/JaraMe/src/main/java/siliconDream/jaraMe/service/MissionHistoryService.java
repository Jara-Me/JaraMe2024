package siliconDream.jaraMe.service;

import siliconDream.jaraMe.dto.ReactionNoticeDTO;

public interface MissionHistoryService {

    ReactionNoticeDTO findTotalNewReactionNumberByUserId(Long userId);

}
