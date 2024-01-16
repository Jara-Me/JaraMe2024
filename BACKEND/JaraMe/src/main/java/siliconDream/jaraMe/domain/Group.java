
package siliconDream.jaraMe.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter

@Table
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,unique = true)
    private Long groupId;

    private String groupName;
    private String missionName;
    private String explanation;
    private String rule;
    private String groupProfileImage;
    private int maxMember;
    private boolean display;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String recurrence;

    private LocalDateTime certificationDay; // Mission-related property

    //TODO: getter and setter
    public Long getGroupId() {
        return groupId;
    }


    @ManyToMany
    private Set<Account> managers = new HashSet<>();

    public void addManager(Account account) {
        managers.add(account);
    }

    // ... other methods

    public static Group createNewGroup(String groupName, String missionName, String explanation, String rule,
                                       String groupProfileImage, int maxMember, boolean display, LocalDateTime startDate,
                                       LocalDateTime endDate, String recurrence, LocalDateTime certificationDay,
                                       Set<Account> managers) {
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

        // Set managers
        group.managers.addAll(managers);

        return group;
    }

}
