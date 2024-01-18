package siliconDream.jaraMe.service;

import siliconDream.jaraMe.domain.JaraUs;

import java.util.List;

public interface JaraUsService {
    /*커밋 전에 지우기
    JaraUsDTO createNewJaraUs(JaraUsDTO jaraUsDTO, String username);


    JaraUs createNew JaraUs(String jaraUsName, String missionName, String explanation, String rule,
                         String jaraUsProfileImage, int maxMember, boolean display, LocalDate startDate,
                         LocalDate endDate, Set<Recurrence> recurrence,
                         Set<Account> managers);

*/


    /////////////////////////////////////////추가
    List<JaraUs> findEndDateToday();



}
