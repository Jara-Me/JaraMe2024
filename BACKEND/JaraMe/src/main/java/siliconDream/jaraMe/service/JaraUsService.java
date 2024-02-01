package siliconDream.jaraMe.service;

import jakarta.validation.Valid;
import siliconDream.jaraMe.domain.JaraUs;
import siliconDream.jaraMe.dto.JaraUsDTO;

import java.util.List;

public interface JaraUsService {


    JaraUs createNewJaraUs(JaraUsDTO jaraUsDTO, Long userId);

    void participateInJaraUs(@Valid JaraUsDTO jaraUsId, Long userId);

    void withdrawFromJaraUs(Long jaraUsId, Long userId);

    void editJaraUsByAdmin(Long jaraUsId, Long adminUserId, JaraUsDTO jaraUsDTO);

    JaraUs editJaraUsInformation(Long userId, JaraUsDTO jaraUsDTO);

    List<JaraUs> findExpiredJaraUs();

    // 검색 기능 추가
    List<JaraUsDTO> searchJaraUs(String keyword);




    /////////////////////////////////////////추가
    List<JaraUs> findEndDateYesterDay();


}
