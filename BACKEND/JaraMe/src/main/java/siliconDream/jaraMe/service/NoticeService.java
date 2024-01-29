package siliconDream.jaraMe.service;


import siliconDream.jaraMe.dto.CalendarDTO;
import siliconDream.jaraMe.dto.NoticeDTO;

import java.time.LocalDate;
import java.util.Optional;

public interface NoticeService {
    Optional<NoticeDTO> findNoticeMessageByUserIdAndNoticeStatus(Long userId);

    Optional<CalendarDTO> getCalendar(LocalDate selectedDate, Long userId);
}
