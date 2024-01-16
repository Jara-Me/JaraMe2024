package siliconDream.jaraMe.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.HashSet;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Group {
    @Id
    @GeneratedValue
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

    @Enumerated
    private Recurrence recurrence;

    private LocalDateTime certificationDay; // Mission-related property


    public static Group createNewGroup(String groupName, String missionName, String explanation, String rule,
                                       String groupProfileImage, int maxMember, boolean display, LocalDateTime startDate,
                                       LocalDateTime endDate, Recurrence recurrence, LocalDateTime certificationDay) {
        Group group = new Group();
        group.groupName = groupName;
        group.missionName = missionName;
        group.explanation = explanation;
        group.rule = rule;
        group.groupProfileImage = groupProfileImage;
        group.maxMember = maxMember;
        group.display = display;
        group.startDate = startDate;
        group.endDate = endDate;
        group.recurrence = recurrence;
        group.certificationDay = certificationDay;


        return group;
    }
}
