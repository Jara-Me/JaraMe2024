package siliconDream.jaraMe.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import siliconDream.jaraMe.dto.GetPassTicketDTO;
import siliconDream.jaraMe.repository.MissionHistoryRepository;
import siliconDream.jaraMe.service.PassTicketService;
import siliconDream.jaraMe.service.UserService;

import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("/passTicket")
public class PassTicketController {
    private final UserService userService;
    private final MissionHistoryRepository missionHistoryRepository;
    private final PassTicketService passTicketService;

    public PassTicketController(UserService userService,
                                MissionHistoryRepository missionHistoryRepository,
                                PassTicketService passTicketService) {
        this.userService = userService;
        this.missionHistoryRepository = missionHistoryRepository;
        this.passTicketService = passTicketService;
    }

    //패스권 수량 및 선택가능한 날 알려주기
    //TODO: missionHistory테이블에 인증 상태 컬럼도 추가하기 .
    // => 미션완주 로직에는 인증을 완료한 경우만 필터링해서 얻어오도록 하고,
    // 여기서는 인증을 완료하지않은 경우만 통계를 내서 전달  (groupBy missionDate로 통계내면 될 듯)
    @GetMapping("/get")
    public GetPassTicketDTO getPassTicketAmount(@RequestParam Long userId){
       return passTicketService.getPassTicket(userId);
    }

    //패스권 사용하기
    //TODO: missionHistory 테이블 레코드를 업데이트 하면 됨 => missionPostId는 null가능하도록 수정하기.
    //
    @PostMapping("/use")
    public String usePassTicket(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate selectedDate , @RequestParam Long userId){
        boolean result = passTicketService.usePassTicket(userId,selectedDate);
        if (result){
            return (String.format("%s에 패스권이 사용되었습니다.",selectedDate.toString()));
        }
    else{return "실패했습니다.";}}

}
