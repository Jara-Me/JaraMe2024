package siliconDream.jaraMe.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity

@Table(name = "'group'")
public class Group {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long groupId;

    private String groupName;

    private String missionName;

    private String groupProfileImage;

    private String explanation;

    private String rule;

    private Long maxMember;

    private boolean display;

    private LocalDateTime startDate;

    private LocalDateTime endDate;


    //TODO: getter and setter

    public Long getGroupId() {
        return groupId;
    }
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
=======
package siliconDream.jaraMe.domain;

import jakarta.persistence.Entity;
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
    private String recurrence;

    private LocalDateTime certificationDay; // Mission-related property

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
>>>>>>> parent of 80b523e (Revert "커밋")
