package siliconDream.jaraMe.dto;

public class UserProfileInfoDTO {
    private int points;
    private int passTickets;
    private int participatingJaraUsCount;

    // 생성자
    public UserProfileInfoDTO(int points, int passTickets, int participatingJaraUsCount) {
        this.points = points;
        this.passTickets = passTickets;
        this.participatingJaraUsCount = participatingJaraUsCount;
    }

    // Getter 메소드
    public int getPoints() {
        return points;
    }

    public int getPassTickets() {
        return passTickets;
    }

    public int getParticipatingJaraUsCount() {
        return participatingJaraUsCount;
    }
}
