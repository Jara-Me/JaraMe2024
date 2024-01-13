package siliconDream.jaraMe.dao;

import siliconDream.jaraMe.domain.User;

public interface PointDAO {
    User findById(Long userId);
    boolean updatePassTicket(Long userId);
    boolean updateCheckIn(Long userId);

}
