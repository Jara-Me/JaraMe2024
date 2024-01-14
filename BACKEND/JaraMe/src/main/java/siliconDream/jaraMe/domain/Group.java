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