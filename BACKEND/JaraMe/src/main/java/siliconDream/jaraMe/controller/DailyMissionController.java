package siliconDream.jaraMe.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import siliconDream.jaraMe.dto.DailyMissionDTO;
import siliconDream.jaraMe.service.DailyMissionService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dailyMission")
public class DailyMissionController {
    private final DailyMissionService dailyMissionService;

    @Autowired
    public DailyMissionController(DailyMissionService dailyMissionService) {
        this.dailyMissionService = dailyMissionService;
    }

    //오늘의 미션 조회 => 테스트완료 / 예외처리 전
    @GetMapping("/get")
    public Optional<List<DailyMissionDTO>> getDailyMission(@SessionAttribute(name="userId", required=true) Long userId){
        Optional<List<DailyMissionDTO>> dailyMissionDTOList = dailyMissionService.getDailyMission(userId, LocalDate.now());
        return dailyMissionDTOList;
    }
}
