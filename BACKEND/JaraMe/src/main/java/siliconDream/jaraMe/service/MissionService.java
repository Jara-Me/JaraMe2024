package siliconDream.jaraMe.service;

import org.springframework.beans.factory.annotation.Autowired;
import siliconDream.jaraMe.dao.MissionDAO;
import siliconDream.jaraMe.dto.MissionPostDTO;
import siliconDream.jaraMe.dto.ReturnMissionPostDTO;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.Optional;

public class MissionService {
    private final MissionDAO missionDAO;

    @Autowired
    public MissionService(MissionDAO missionDAO){
        this.missionDAO=missionDAO;
    }

    //TODO: 미션 인증글 작성
    public boolean missionPost(MissionPostDTO missionPost){
    Long userId = missionPost.getUserId();
    Long groupId = missionPost.getGroupId();
    LocalDateTime dateTime = missionPost.getDateTime();
    boolean display = missionPost.getDisplay();
    boolean anonymous = missionPost.getAnonymous();
    String title = missionPost.getTitle();
    String textContent = missionPost.getTextContent();
    String imageContent = missionPost.getImageContent();

    //미션 인증글 저장
    Optional<ReturnMissionPostDTO> returnMissionPostDTO =missionDAO.saveMissionPost(userId, groupId,dateTime,display, anonymous, title, textContent, imageContent);



    return true;
}}
