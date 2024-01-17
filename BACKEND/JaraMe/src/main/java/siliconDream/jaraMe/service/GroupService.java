package siliconDream.jaraMe.service;

import siliconDream.jaraMe.domain.Group;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import siliconDream.jaraMe.domain.Recurrence;
import siliconDream.jaraMe.dto.GroupDTO;

public interface GroupService {
    GroupDTO createNewGroup(GroupDTO groupDTO, String username);


    Group createNewGroup(String groupName, String missionName, String explanation, String rule,
                         String groupProfileImage, int maxMember, boolean display, LocalDate startDate,
                         LocalDate endDate, Set<Recurrence> recurrence,
                         Set<Account> managers);




    /////////////////////////////////////////추가
    List<Group> findEndDateToday();



}
