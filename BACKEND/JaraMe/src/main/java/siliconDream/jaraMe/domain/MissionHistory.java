package siliconDream.jaraMe.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class MissionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,unique = true)
    private Long missionHistoryId;

    private LocalDate missionDate;

    //fk
    @JoinColumn(name="user")
    @ManyToOne
    private User user;

    @JoinColumn(name="jaraUs")
    @ManyToOne
    private JaraUs jaraUs;

}


