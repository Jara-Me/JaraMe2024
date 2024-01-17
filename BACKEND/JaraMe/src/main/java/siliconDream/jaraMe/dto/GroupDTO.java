package siliconDream.jaraMe.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import siliconDream.jaraMe.domain.Recurrence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
public class GroupDTO {

    public GroupDTO(Long groupId, String groupName, String missionName, String explanation, String rule,
                    String groupProfileImage, int maxMember, boolean display, LocalDate startDate,
                    LocalDate endDate, Set<Recurrence> recurrenceSet) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.missionName = missionName;
        this.explanation = explanation;
        this.rule = rule;
        this.groupProfileImage = groupProfileImage;
        this.maxMember = maxMember;
        this.display = display;
        this.startDate = startDate;
        this.endDate = endDate;
        this.recurrence = recurrence;
    }

    private Long groupId;
    private String groupName;
    private String missionName;
    private String explanation;
    private String rule;
    private String groupProfileImage;
    private int maxMember;
    private boolean display;
    private LocalDate startDate;
    private LocalDate endDate;
    private Set<Recurrence> recurrence;


    //TODO: getter and setter
    public Set<Recurrence> getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(Set<Recurrence> recurrence) {
        this.recurrence = recurrence;
    }

}