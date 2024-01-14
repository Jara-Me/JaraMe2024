package siliconDream.jaraMe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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


}
