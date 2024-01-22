package siliconDream.jaraMe.service;

import org.springframework.stereotype.Service;
import siliconDream.jaraMe.domain.User;
import siliconDream.jaraMe.repository.PointRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PointServiceImpl implements PointService {
    private final PointRepository pointRepository;

    public PointServiceImpl(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    //출석체크
    public String checkIn(Long userId, LocalDateTime dateTime) {
        //boolean checkInResult = false;
        String resultMessage = "";
        Optional<User> user = pointRepository.findByUserId(userId);
        if (user.isPresent()) {
            if (dateTime.toLocalDate().equals(LocalDate.now())) { //오늘날짜가 맞는지
                //오늘 출석체크한 적이 없는지
                if (pointRepository.findByUserId(userId).get().isCheckIn() == false) {
                    pointRepository.updateCheckIn(userId);
                    resultMessage = "출석체크되었습니다! (+2포인트)"; //출석체크 성공
                } else if (pointRepository.findByUserId(userId).get().isCheckIn() == true) {
                    resultMessage = "오늘 이미 출석체크를 하셨습니다!";
                }

            } else if (!dateTime.toLocalDate().equals(LocalDate.now())) {
                resultMessage = "출석체크는 요청 날짜가 잘못됐습니다";
            }
        }
        return resultMessage;
    }

    //패스권 구매
    public String passTicket(Long userId) {
        //dao를 통해 userId로 해당 레코드 가져온 후, point컬럼 값 추출하기
        String resultMessage = "";
        Optional<User> user = pointRepository.findById(userId);
        int point = user.get().getPoint();

        if (point >= 60) {
            //dao 통해 passTicket은 +1, point는 -60
            pointRepository.updatePassTicket(userId);
            resultMessage = "패스권 구입에 성공했습니다!(-60포인트)";

        } else if (point < 60) {
            resultMessage = "포인트가 부족합니다.";
        }
        return resultMessage;
    }

    //참여율에 따라 포인트 지급 (pointService? )
    public int pointPlus(Long userId, int changeAmount) {
        int updatedPoint = 0;

        updatedPoint = pointRepository.plusPoint(userId, changeAmount);

        return updatedPoint;
    }
}
