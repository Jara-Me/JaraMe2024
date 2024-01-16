package siliconDream.jaraMe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import siliconDream.jaraMe.service.PointService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/point")
public class PointController {
    private final PointService pointService;


    @Autowired
    public PointController(PointService pointService) {
        this.pointService = pointService;
    }


    //TODO: 출석체크
    @PostMapping("/checkIn") //@ResponseBody
    public String checkIn(@RequestParam Long userId, @RequestParam @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss") LocalDateTime dateTime) {
        //return pointService.checkIn(userId, dateTime);
        boolean httpResponse = pointService.checkIn(userId, dateTime);
        if (httpResponse) {
            return "출석체크 포인트가 지급되었습니다!";
        } else {
            //throw new CustomException(POINT_NOT_FOUND);
            return "출석체크에 실패하였습니다!";
        }
    }

    //TODO: 패스권 구매
    @PostMapping("/passTicket")
    @ResponseBody
    public ResponseEntity passTicket(@RequestParam Long userId) throws Exception {
        //HttpHeaders httpHeaders = new HttpHeaders();

        boolean httpResponse = pointService.passTicket(userId);
        if (httpResponse) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            //throw new CustomException(POINT_NOT_FOUND);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //스토어 구매 api => 아이템 정해져야 할 수 있음

    //패스권 사용 api => Point관련된 로직은 아니라서 다른 Controller가 적합할 것같음.
}