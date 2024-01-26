package siliconDream.jaraMe.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Entity
@Table
public class MissionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,unique = true)
    private Long missionHistoryId;

    private LocalDate missionDate;
    private boolean missionResult;
    //fk
    @JoinColumn(name="user")
    @ManyToOne
    private User user;

    @JoinColumn(name="jaraUs")
    @ManyToOne
    private JaraUs jaraUs;

    @JoinColumn(name="missionPost")
    @OneToOne
    private MissionPost missionPost;


}


