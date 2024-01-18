package siliconDream.jaraMe.service;

import siliconDream.jaraMe.domain.JaraUs;
import siliconDream.jaraMe.domain.Recurrence;
import siliconDream.jaraMe.domain.User;
import siliconDream.jaraMe.dto.JaraUsDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface JaraUsService {

    //JaraUsServiceImpl 에서 에러나서 주석처리한 함수 (JaraUsServiceImpl이 JaraUsService를 implements => 함수 구성이 똑같아야해서 아래의 함수를 일단 주석처리함.)
    // JaraUsDTO createNewJaraUs(JaraUsDTO jaraUsDTO, String userId);

/* 충돌났던 부분 -> 일단 주석처리
    JaraUs createNewJaraUs(String jaraUsName, String missionName, String explanation, String rule,
                            String jaraUsProfileImage, int maxMember, boolean display, LocalDate startDate,
                            LocalDate endDate,
                            Set<Account> managers); //Set<Recurrence> recurrence,추가 예정
*/



    /////////////////////////////////////////추가
    List<JaraUs> findEndDateToday();



}
