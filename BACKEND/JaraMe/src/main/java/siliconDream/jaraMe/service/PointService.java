package siliconDream.jaraMe.service;

import org.springframework.stereotype.Service;
import siliconDream.jaraMe.dao.PointDAOImpl;
import siliconDream.jaraMe.domain.User;

import java.time.LocalDateTime;

@Service
public class PointService {
    private final PointDAOImpl pointDAO;

    public PointService(PointDAOImpl pointDAO) {
        this.pointDAO = pointDAO;
    }

    public boolean checkIn(Long userId, LocalDateTime dateTime) {
        boolean checkInResult=false;
        User user = pointDAO.findById(userId);
        //TODO: checkInHistory 테이블/도메인 추가하기
        //TODO: user 테이블에 하루에 한번 초기화되는 checkIn컬럼 추가하기
        if (user.getCheckIn()==false){
            boolean updateResult=pointDAO.updateCheckIn(userId);
            checkInResult=updateResult;
            return checkInResult;

        }else {return checkInResult;}

    }

    public boolean passTicket(Long userId) {
        //dao를 통해 userId로 해당 레코드 가져온 후, point컬럼 값 추출하기
        boolean passTicketResult=false;
        User user= pointDAO.findById(userId);
        Long point = user.getPoint();

        if (point >= 60) {
            //dao 통해 passTicket은 +1, point는 -60
            boolean updateResult = pointDAO.updatePassTicket(userId);
            passTicketResult=updateResult;
            return passTicketResult;
        }else {return passTicketResult;}
    }


}
