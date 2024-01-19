package siliconDream.jaraMe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import siliconDream.jaraMe.domain.Sample;
import siliconDream.jaraMe.dto.GetMissionPostDTO;
import siliconDream.jaraMe.dto.MissionPostDTO;
import siliconDream.jaraMe.dto.sampleDTO;
import siliconDream.jaraMe.repository.JaraUsRepository;
import siliconDream.jaraMe.repository.SampleRepository;
import siliconDream.jaraMe.service.MissionPostService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class sampleJaraUsController {

    private final SampleRepository sampleRepository;


    @Autowired
    public sampleJaraUsController(SampleRepository sampleRepository) {

        this.sampleRepository = sampleRepository;
    }


    @PostMapping("/recurrence")
    public List<Sample> recurrence(@RequestBody sampleDTO sampleJaraUsDTO) {
        List<Sample> sampleList= sampleRepository.saveSample(sampleJaraUsDTO);
        return sampleList;
    }
}