package siliconDream.jaraMe.dto;

public class DailyMissionDTO {
    //미션 이름, 자라어스 이름, 미션 수행 상황 (f는 인증바로가기, f는 인증완료)



    private boolean dailyMissionResult;
    private String jaraUsName;

    public boolean isDailyMissionResult() {
        return dailyMissionResult;
    }

    public void setDailyMissionResult(boolean dailyMissionResult) {
        this.dailyMissionResult = dailyMissionResult;
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

    private String missionName;

    public DailyMissionDTO() {
    }

    public DailyMissionDTO(boolean dailyMissionResult, String jaraUsName, String missionName) {
        this.dailyMissionResult = dailyMissionResult;
        this.jaraUsName = jaraUsName;
        this.missionName = missionName;
    }


}
