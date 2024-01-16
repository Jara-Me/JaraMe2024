package siliconDream.jaraMe.service;

import siliconDream.jaraMe.domain.Group;

import java.time.LocalDateTime;
import java.util.List;

public interface GroupService {
    /*
    Group createNewGroup(String groupName, String missionName, String explanation, String rule,
                         String groupProfileImage, int maxMember, boolean display, LocalDateTime startDate,
                         LocalDateTime endDate, String recurrence, LocalDateTime certificationDay,
                         Set<Account> managers);


*/

    /////////////////////////////////////////추가
    List<Group> findEndDateToday();



}
