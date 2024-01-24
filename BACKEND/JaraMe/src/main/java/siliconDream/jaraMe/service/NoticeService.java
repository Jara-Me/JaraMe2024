package siliconDream.jaraMe.service;


import siliconDream.jaraMe.dto.NoticeDTO;

import java.util.Optional;

public interface NoticeService {
    Optional<NoticeDTO> findNoticeMessageByUserIdAndNoticeStatus(Long userId);
}
