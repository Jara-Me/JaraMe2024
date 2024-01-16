package siliconDream.jaraMe.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import siliconDream.jaraMe.domain.Recurrence;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class GroupDTO {
    private Long id;
    private String groupName;
    private String missionName;
    private String explanation;
    private String rule;
    private String groupProfileImage;
    private int maxMember;
    private boolean display;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Recurrence recurrence;
    private LocalDateTime certificationDay;
}