package siliconDream.jaraMe.service;

import siliconDream.jaraMe.domain.JaraUs;
import siliconDream.jaraMe.domain.Recurrence;
import siliconDream.jaraMe.domain.User;
import siliconDream.jaraMe.dto.JaraUsDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface JaraUsService {

    JaraUs createNewJaraUs(JaraUsDTO jaraUsDTO);

    void participateInJaraUs(Long jaraUsId, Long userId);

    void withdrawFromJaraUs(Long jaraUsId, Long userId);

    void editJaraUsByAdmin(Long jaraUsId, Long adminUserId, JaraUsDTO jaraUsDTO);

    JaraUs editJaraUsInformation(Long userId, JaraUsDTO jaraUsDTO);

    List<JaraUs> findExpiredJaraUs();

    // 검색 기능 추가
    List<JaraUsDTO> searchJaraUs(String keyword);




    /////////////////////////////////////////추가
    List<JaraUs> findEndDateYesterDay();


}
