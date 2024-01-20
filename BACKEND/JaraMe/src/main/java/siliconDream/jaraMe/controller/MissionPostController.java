package siliconDream.jaraMe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import siliconDream.jaraMe.dto.GetMissionPostDTO;
import siliconDream.jaraMe.dto.MissionPostDTO;
import siliconDream.jaraMe.service.MissionPostService;

import java.util.Optional;

@RestController
@RequestMapping("/mission")
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




}
