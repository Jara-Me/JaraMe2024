package siliconDream.jaraMe.dto;

import java.time.LocalDateTime;
import java.util.List;

public class GetMissionPostDTO {

    //TODO: 인증글
    private Long missionPostId;
    private String textTitle;
    private String textContent;
    private String imageContent;    //인증글 이미지 파일 => 몇장까지 첨부할지에 따라 구조가 달라질 수도 있음.
    private LocalDateTime postDateTime;

    private String nickname;
    private String profileImage;

    //TODO: 댓글
    private List<CommentDTO> CommentDTO;



    //TODO: 리액션
    private List<ReactionDTO> ReactionDTO;
//        private Long reactionLike=0L;
//        private Long reactionThumb=0L;
//        private Long reactionSmile=0L;














}

