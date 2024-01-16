package siliconDream.jaraMe.service;

import siliconDream.jaraMe.domain.Group;

import java.time.LocalDateTime;

public interface GroupService {
    Group createNewGroup(String groupName, String missionName, String explanation, String rule,
                         String groupProfileImage, int maxMember, boolean display, LocalDateTime startDate,
                         LocalDateTime endDate, String recurrence, LocalDateTime certificationDay,
                         Set<Account> managers);
}
