package siliconDream.jaraMe.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;


@Entity

@Table
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable = false,unique = true)
    private Long userId;

    @Getter
    @Column(nullable = true)
    private String profileImage;

    @Getter
    @Column(length=20, nullable = false, columnDefinition = "VARCHAR (255)")
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable=true)
    private LocalDate birthDate;

    @Column(columnDefinition = "DEFAULT FALSE")
    private boolean checkIn;

    private Long point=0L;

    private Long passTicket=0L;


    //FK
    @OneToMany(mappedBy="user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<MissionPost> missionPost;

    @OneToMany(mappedBy="user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Comment> comment;

    @OneToMany(mappedBy="user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Reaction> reaction;


    //FK
    @OneToMany(mappedBy="user")
    @JsonIgnore
    private List<JoinUserList> joinUserList;

    @OneToMany(mappedBy="user")
    @JsonIgnore
    private List<MissionHistory> missionHistory;

    @OneToMany(mappedBy="user")
    @JsonIgnore
    private List<ToDoList> toDoList;


    //TODO: getter and setter
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    public String getProfileImage() { return profileImage; }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public boolean getCheckIn() { return checkIn; }
    public void setCheckIn(boolean checkIn) {
        this.checkIn = checkIn;
    }

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

}
