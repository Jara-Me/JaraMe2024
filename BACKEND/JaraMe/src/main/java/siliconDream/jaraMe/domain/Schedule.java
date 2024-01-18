package siliconDream.jaraMe.domain;

import jakarta.persistence.*;

@Entity
@Table
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,unique = true)
    private Long scheduleId;
    //마저 작성 예정
}
