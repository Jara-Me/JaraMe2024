package siliconDream.jaraMe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import siliconDream.jaraMe.dto.GetMissionPostDTO;
import siliconDream.jaraMe.dto.MissionPostDTO;
import siliconDream.jaraMe.service.MissionService;

import java.util.Optional;

@RestController
@RequestMapping("/mission")
public class MissionController {
    private final MissionService missionService;

    @Autowired
    public MissionController(MissionService missionService){
        this.missionService=missionService;
    }

    //TODO: 미션 인증글 작성
    @PostMapping("/post")
    public Optional<GetMissionPostDTO> missionPost(@RequestBody MissionPostDTO missionPostDTO){
        Optional<GetMissionPostDTO> getMissionPostDTO=missionService.missionPost(missionPostDTO );



    return getMissionPostDTO;
    }
    /*
    //TODO: 미션 완주
    @PostMapping("/missionFinish")
    @ResponseBody
    public ResponseEntity missionFinish(@RequestParam Long userId, @RequestParam Long groupId) {
        boolean httpResponse = missionService.missionFinish(userId, groupId);
        if (httpResponse) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            //throw new CustomException(POINT_NOT_FOUND);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
*/
    //TODO: 오늘의 미션 완료
    @PostMapping("/dailyMissionFinish")
    @ResponseBody
    public ResponseEntity dailyMissionFinish(@RequestParam Long userId, @RequestParam Long groupId) {

        boolean httpResponse = missionService.dailyMissionFinish(userId, groupId);
        if (httpResponse) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            //throw new CustomException(POINT_NOT_FOUND);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }


}
