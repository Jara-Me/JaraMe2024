package siliconDream.jaraMe.dto;

import java.time.LocalDateTime;

public class CommentDTO {

    public CommentDTO(Long commentId,
                      String commentContent,
                      LocalDateTime commentDate,
                      String nickname
            , String profileImage) {
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.commentDate = commentDate;
        this.nickname = nickname;
        this.profileImage = profileImage;
    }

    private Long commentId;

    private String commentContent;

    private LocalDateTime commentDate;

    private String nickname;

    private String profileImage;


}
