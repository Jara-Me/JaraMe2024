package siliconDream.jaraMe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import siliconDream.jaraMe.dto.GetMissionPostDTO;
import siliconDream.jaraMe.dto.MissionPostDTO;
import siliconDream.jaraMe.service.MissionPostService;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/missionPost")
public class MissionPostController {
    private final MissionPostService missionService;

    @Autowired
    public MissionPostController(MissionPostService missionService) {
        this.missionService = missionService;
    }

    //미션 인증글 등록
    @PostMapping("/post")
    public Optional<GetMissionPostDTO> missionPost(@RequestBody MissionPostDTO missionPostDTO) {
        Optional<GetMissionPostDTO> getMissionPostDTO = missionService.missionPost(missionPostDTO);


        return getMissionPostDTO;
    }


    //미션 인증글 조회
    @GetMapping("/get")
    public Optional<GetMissionPostDTO> getMissionPost(@RequestParam Long missionPostId){ //전체 인증글 목록에서 얻어내기
        Optional<GetMissionPostDTO> getMissionPostDTO = missionService.getMissionPostDetails(missionPostId);
        return getMissionPostDTO;
    }

    //미션 인증글 수정
    //TODO: 오늘=> 내용과 공개/익명 정보 모두 수정 가능
    //TODO: 오늘이 아닌 경우 => 공개/익명 정보만 수정 가능
    @PostMapping("/update")
    public Optional<GetMissionPostDTO> updateMissionPost(@RequestParam Long missionPostId, @RequestBody MissionPostDTO missionPostDTO, Long userId){

        String resultMessage = missionService.updateMissionPost(missionPostId,missionPostDTO, userId, LocalDate.now());
        //Optional<GetMissionPostDTO> getMissionPostDTO

        //수행결과에 따라 (수정되거나 수정되지않은) 미션 인증글 조회 결과 반환
        if (resultMessage=="수정되었습니다.") {
            Optional<GetMissionPostDTO> getMissionPostDTO = missionService.getMissionPostDetails(missionPostId); //TODO: 반환값 메시지 담기게 수정예정!!
            return getMissionPostDTO;
        }else { //수행결과로 받은 반환값인 resultMessage 들어가도록 수정하기
            Optional<GetMissionPostDTO> getMissionPostDTO = missionService.getMissionPostDetails(missionPostId); //수정 예정
            return getMissionPostDTO;
             }


    }

    //미션 인증글 삭제
    //TODO: 미션 진행 중일 경우 삭제 불가능 (포인트 미지급 예방)
    //TODO: 미션 종료 후 삭제 가능

}
