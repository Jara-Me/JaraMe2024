package siliconDream.jaraMe.service;

import siliconDream.jaraMe.dto.GroupDTO;

public interface GroupService {
    GroupDTO createNewGroup(GroupDTO groupDTO, String username);
}
