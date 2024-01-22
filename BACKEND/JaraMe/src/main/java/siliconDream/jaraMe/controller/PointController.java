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


    //출석체크
    @PostMapping("/checkIn") //@ResponseBody
    public String checkIn(@RequestParam Long userId, @RequestParam @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss") LocalDateTime dateTime) {
        //return pointService.checkIn(userId, dateTime);
        String responseMessage = pointService.checkIn(userId, dateTime);
        return responseMessage;
    }

    //패스권 구매
    @PostMapping("/passTicket")
    public String passTicket(@RequestParam Long userId) {
        //HttpHeaders httpHeaders = new HttpHeaders();

        String resultMessage = pointService.passTicket(userId);
        return resultMessage;
    }


    //패스권 사용 api => Point관련된 로직은 아니라서 다른 Controller가 적합할 것같음.
}