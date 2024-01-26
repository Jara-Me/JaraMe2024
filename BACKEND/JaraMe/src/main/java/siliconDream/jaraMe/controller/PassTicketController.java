package siliconDream.jaraMe.controller;

import org.springframework.web.bind.annotation.*;
import siliconDream.jaraMe.domain.GetPassTicketDTO;
import siliconDream.jaraMe.repository.MissionHistoryRepository;
import siliconDream.jaraMe.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/passTicket")
public class PassTicketController {
    private final UserService userService;
    private final MissionHistoryRepository missionHistoryRepository;


    public PassTicketController(UserService userService,
                                MissionHistoryRepository missionHistoryRepository) {
        this.userService = userService;
        this.missionHistoryRepository = missionHistoryRepository;
    }

    //패스권 수량 및 선택가능한 날 알려주기
    //TODO: missionHistory테이블에 인증 상태 컬럼도 추가하기 .
    // => 미션완주 로직에는 인증을 완료한 경우만 필터링해서 얻어오도록 하고,
    // 여기서는 인증을 완료하지않은 경우만 통계를 내서 전달  (groupBy missionDate로 통계내면 될 듯)
    @GetMapping("/get")
    public GetPassTicketDTO getPassTicketAmount(@RequestParam Long userId){
        GetPassTicketDTO getPassTicketDTO = new GetPassTicketDTO();
        getPassTicketDTO.setPassTicket(userService.getPassTicket(userId));


        /*   Optional<List<Object[]>> reactionCountDTOs = reactionRepository.findByMissionPost_MissionPostId(oneMissionPostId);
                if (reactionCountDTOs.isPresent()) {
                    for (Object[] object : reactionCountDTOs.get()) {
                        for (int i = 0; i < 3; i++) {
                            if (object[0].equals("smile")) {
                                smile = ((Number) object[1]).intValue();
                            }
                            else if (object[0].equals("like")) {
                                like = ((Number) object[1]).intValue();
                            }else if (object[0].equals("good")) {
                                good = ((Number) object[1]).intValue();
                            }


                    }
                }

                    ReactionNoticeDTO reactionNoticeDTO = new ReactionNoticeDTO(oneMissionPostId,
                            missionPostRepository.findByMissionPostId(oneMissionPostId).getTextTitle(),
                            like,
                            good,
                            smile);
*/


        getPassTicketDTO.setUndoneDateDTOs(missionHistoryRepository.findMissionDateByUser_UserIdAndMissionResult(userId,false));
        return getPassTicketDTO;
    }
/*
    //패스권 사용하기
    //TODO: missionHistory 테이블 레코드를 업데이트 하면 됨 => missionPostId는 null가능하도록 수정하기.
    //
    @PostMapping("/use")
    public String usePassTicket(@RequestBody PassTicketDTO passTicketDTO, @RequestParam Long userId){

    }
*/
}
