package siliconDream.jaraMe.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import siliconDream.jaraMe.domain.Group;
import siliconDream.jaraMe.repository.GroupRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    @Override
    public Group createNewGroup(String groupName, String missionName, String explanation, String rule,
                                String groupProfileImage, int maxMember, boolean display, LocalDateTime startDate,
                                LocalDateTime endDate, String recurrence, LocalDateTime certificationDay,
                                Set<Account> managers) {
        Group group = Group.createNewGroup(groupName, missionName, explanation, rule, groupProfileImage,
                maxMember, display, startDate, endDate, recurrence, certificationDay, managers);

        // Save the group
        return groupRepository.save(group);
    }
}