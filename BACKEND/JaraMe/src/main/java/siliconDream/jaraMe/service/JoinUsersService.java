package siliconDream.jaraMe.service;

import siliconDream.jaraMe.domain.User;

import java.util.List;

public interface JoinUsersService {

    public List<Long> findUserIdsByGroupId(Long groupId);
}
