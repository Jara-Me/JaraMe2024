package siliconDream.jaraMe.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.awt.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity

@Table
public class User {
    //@Column(nullable=true,unique=true)
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable = false,unique = true)
    private Long id;

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    @Column(nullable=true)
    private String profileImage;

    public String getNickname() {
        return nickname;
    }

    @Column(length=20,nullable = false)//,columnDefinition = "VARCHAR (255)")
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    //@Column(nullable=true)
    private LocalDate birthDate;

    public boolean getCheckIn() {
        return checkIn;
    }


    public void setCheckIn(boolean checkIn) {
        this.checkIn = checkIn;
    }

    //하루에 한번 초기화되도록 설정하기
    @Column(columnDefinition = "DEFAULT FALSE")
    private boolean checkIn;


    public Long getPoint() {
        return point;
    }

    public void setPoint(Long point) {
        this.point = point;
    }

    public Long getPassTicket() {
        return passTicket;
    }

    public void setPassTicket(Long passTicket) {
        this.passTicket = passTicket;
    }

    private Long point=0L;

    private Long passTicket=0L;
/*
    //FK
    @OneToMany(mappedBy="user")
    @JsonIgnore
    private List<JoinUserList> joinUserList;

    @OneToMany(mappedBy="user")
    @JsonIgnore
    private List<MissionPost> missionPost;

    @OneToMany(mappedBy="user")
    @JsonIgnore
    private List<Comment> comment;

    @OneToMany(mappedBy="user")
    @JsonIgnore
    private List<Reaction> reaction;

    @OneToMany(mappedBy="user")
    @JsonIgnore
    private List<Purchase> purchase;

    @OneToMany(mappedBy="user")
    @JsonIgnore
    private List<MissionHistory> missionHistory;

    @OneToMany(mappedBy="user")
    @JsonIgnore
    private List<ToDoList> toDoList;

 */
}
