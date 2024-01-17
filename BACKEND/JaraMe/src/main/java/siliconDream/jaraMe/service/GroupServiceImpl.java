package siliconDream.jaraMe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import siliconDream.jaraMe.domain.Group;
import siliconDream.jaraMe.domain.Recurrence;
import siliconDream.jaraMe.dto.GroupDTO;
import siliconDream.jaraMe.repository.GroupRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    @Override
    public GroupDTO createNewGroup(GroupDTO groupDTO, String username) {
        // Extract relevant information from groupDTO
        String groupName = groupDTO.getGroupName();
        String missionName = groupDTO.getMissionName();
        String explanation = groupDTO.getExplanation();
        String rule = groupDTO.getRule();
        String groupProfileImage = groupDTO.getGroupProfileImage();
        int maxMember = groupDTO.getMaxMember();
        boolean display = groupDTO.isDisplay();
        LocalDate startDate = groupDTO.getStartDate();
        LocalDate endDate = groupDTO.getEndDate();
        Set<Recurrence> recurrence = groupDTO.getRecurrence();



        // Create a new group
        Group group = Group.createNewGroup(groupName, missionName, explanation, rule, groupProfileImage,
                maxMember, display, startDate, endDate, recurrence);

        // Save the group
        Group savedGroup = groupRepository.save(group);

        // Convert the savedGroup to GroupDTO and return
        return convertToDTO(savedGroup);
    }

    // Your logic to convert Group to GroupDTO
    private GroupDTO convertToDTO(Group group) {
        return new GroupDTO(
                group.getGroupId(),
                group.getGroupName(),
                group.getMissionName(),
                group.getExplanation(),
                group.getRule(),
                group.getGroupProfileImage(),
                group.getMaxMember(),
                group.isDisplay(),
                group.getStartDate(),
                group.getEndDate(),
                group.getRecurrence()
        );

    }


    public List<Group> findEndDateToday(){
        return groupRepository.findEndDateToday(LocalDate.now());
    }



}