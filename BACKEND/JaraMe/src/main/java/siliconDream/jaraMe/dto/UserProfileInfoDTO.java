package siliconDream.jaraMe.dto;

public class UserProfileInfoDTO {
    private int points;
    private int passTicket;
    private int participatingJaraUsCount;

    public int getParticipatingJaraUsCount() {
        return participatingJaraUsCount;
    }

    public void setParticipatingJaraUsCount(int participatingJaraUsCount) {
        this.participatingJaraUsCount = participatingJaraUsCount;
    }

    public int getPassTicket() {
        return passTicket;
    }

    public void setPassTicket(int passTicket) {
        this.passTicket = passTicket;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
