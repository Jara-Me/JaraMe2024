
package siliconDream.jaraMe.domain;

<<<<<<< HEAD
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToMany;
=======
import jakarta.persistence.*;
>>>>>>> bb0111f706d92548b8747c92fe2a8abcd4a4ba85
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

    @Enumerated
    private Recurrence recurrence;

    private LocalDateTime certificationDay; // Mission-related property

<<<<<<< HEAD
=======
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
>>>>>>> bb0111f706d92548b8747c92fe2a8abcd4a4ba85

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
