package siliconDream.jaraMe.service;

<<<<<<< HEAD
import siliconDream.jaraMe.dto.GroupDTO;

public interface GroupService {
    GroupDTO createNewGroup(GroupDTO groupDTO, String username);
=======
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



>>>>>>> bb0111f706d92548b8747c92fe2a8abcd4a4ba85
}
