package siliconDream.jaraMe.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import siliconDream.jaraMe.domain.User;

import java.util.Optional;

public interface PointRepository extends JpaRepository<User, Long> {


    //TODO: 레코드 조회
    Optional<User> findByUserId(Long userId) ;


    //TODO: 패스권 구매
    default boolean updatePassTicket(Long userId) {
        try {
            Optional<User> userOptional = findByUserId(userId);
            User user = userOptional.get();
            if (user != null) {
                user.setPoint(user.getPoint() - 60);
                user.setPassTicket((user.getPassTicket()+1));
                save(user);
                 return true;
            } else {
                return false;
            }
        }  catch (EntityNotFoundException e){
            e.printStackTrace();
            return false;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    //TODO: 출석체크
    default boolean updateCheckIn(Long userId){
        try{
            Optional<User> userOptional= findByUserId(userId);

            if (userOptional.isPresent()){
                User user = userOptional.get();
                user.setPoint(user.getPoint()+2);
                user.setCheckIn(true);
                save(user);
                return true;
            }else{return false;}


        } catch (EntityNotFoundException e){
            e.printStackTrace();
            return false;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}