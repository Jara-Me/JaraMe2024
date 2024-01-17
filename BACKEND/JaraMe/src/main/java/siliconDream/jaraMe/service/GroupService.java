package siliconDream.jaraMe.service;

import siliconDream.jaraMe.domain.Group;

import java.time.LocalDateTime;
import java.util.List;

import siliconDream.jaraMe.dto.GroupDTO;

public interface GroupService {
    GroupDTO createNewGroup(GroupDTO groupDTO, String username);


    Group createNewGroup(String groupName, String missionName, String explanation, String rule,
                         String groupProfileImage, int maxMember, boolean display, LocalDateTime startDate,
                         LocalDateTime endDate, String recurrence, LocalDateTime certificationDay,
                         Set<Account> managers);




    /////////////////////////////////////////추가
    List<Group> findEndDateToday();



}
