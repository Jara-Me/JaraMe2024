package siliconDream.jaraMe.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import siliconDream.jaraMe.domain.User;


@Slf4j
@Repository
public class PointDAOImpl implements PointDAO {

    @PersistenceContext
    private EntityManager entityManager;

    //TODO: 레코드 조회
    @Override
    @Transactional
    public User findById(Long userId) {
        return entityManager.find(User.class, userId);
    }

    //TODO: 패스권 구매
    @Override
    @Transactional
    public boolean updatePassTicket(Long userId) {

        try {
            User user = findById(userId);
            log.info("before : user.getPoint:{}",user.getPoint());
            log.info("before : user.getPassTicket:{}",user.getPassTicket());
            //User entityManagerUser = entityManager.find(User.class, userId);

            if (user != null) {
                user.setPoint(user.getPoint() - 60);
                user.setPassTicket((user.getPassTicket()+1));
                entityManager.merge(user);
                log.info("after : user.getPoint:{}",user.getPoint());
                log.info("after : user.getPassTicket:{}",user.getPassTicket());
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    //TODO: 출석체크
    @Override
    @Transactional
    public boolean updateCheckIn(Long userId){
        try{
            User user= entityManager.find(User.class, userId);
            if (user!=null){
                user.setPoint(user.getPoint()+2);
                user.setCheckIn(true);
                entityManager.merge(user);
                return true;
            }else{return false;}


        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}