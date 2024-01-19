package siliconDream.jaraMe.domain;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Set;

@Entity
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter

@Table
public class JaraUs {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable = false,unique = true)
    private Long adminUserId;
    private Long jaraUsId;
    private String jaraUsName;
    private String missionName;
    private String explanation;
    private String rule;
    private String jaraUsProfileImage;
    private int maxMember;
    private boolean display;
    private LocalDate startDate;
    private LocalDate endDate;
    private Set<Recurrence> recurrence;

    //@ElementCollection(targetClass = Recurrence.class, fetch = FetchType.EAGER)
    //@CollectionTable(name = "JaraUsAndRecurrence", joinColumns = @JoinColumn(name = "jaraUsId"))
        //@Enumerated(EnumType.STRING)

    //private Set<Recurrence> recurrence;


/*Account가 없는 상태라 에러나서 주석처리함.
    @ManyToMany
    private Set<Account> managers = new HashSet<>();

    public void addManager(Account account) {
        managers.add(account);
    }

    public static JaraUs createNewJaraUs(String jaraUsName, String missionName, String explanation, String rule,
                                       String jaraUsProfileImage, int maxMember, boolean display, LocalDate startDate,
                                       LocalDate endDate, Set<Recurrence> recurrence) {
        JaraUs jaraUs = new JaraUs();
        jaraUs.jaraUsName = jaraUsName;
        jaraUs.missionName = missionName;
        jaraUs.explanation = explanation;
        jaraUs.rule = rule;
        jaraUs.jaraUsProfileImage = jaraUsProfileImage;
        jaraUs.maxMember = maxMember;
        jaraUs.display = display;
        jaraUs.startDate = startDate;
        jaraUs.endDate = endDate;
        jaraUs.recurrence = recurrence;

        return jaraUs;
    } */

    @ManyToOne
    @JoinColumn(name = "admin_user_id", nullable = false)
    private User administrator;

    //TODO: getter and setter

    public Long getAdminUserId() {
        return adminUserId;
    }
    public void setAdminUserId(User administrator) { this.administrator = administrator; }
    public Long getJaraUsId() {
        return jaraUsId;
    }

    public void setJaraUsId(Long jaraUsId) {
        this.jaraUsId = jaraUsId;
    }

    public String getJaraUsName() {
        return jaraUsName;
    }

    public void setJaraUsName(String jaraUsName) {
        this.jaraUsName = jaraUsName;
    }

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getJaraUsProfileImage() {
        return jaraUsProfileImage;
    }

    public void setJaraUsProfileImage(String jaraUsProfileImage) {
        this.jaraUsProfileImage = jaraUsProfileImage;
    }

    public int getMaxMember() {
        return maxMember;
    }

    public void setMaxMember(int maxMember) {
        this.maxMember = maxMember;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Set<Recurrence> getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(Set<Recurrence> recurrence) {
        this.recurrence = recurrence;
    }
}
