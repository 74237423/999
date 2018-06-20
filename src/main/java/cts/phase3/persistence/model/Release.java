package cts.phase3.persistence.model;

import java.io.Serializable;

/**
 * @program: CTS2018_Phase_III
 * @author: Lijie
 * @description: 发布任务
 * @create:
 **/
public class Release implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String missionName;

    private String announcerName;

    private String start;

    private String end;

    private int state;

    private int checkFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public String getAnnouncerName() {
        return announcerName;
    }

    public void setAnnouncerName(String announcerName) {
        this.announcerName = announcerName;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(int checkFlag) {
        this.checkFlag = checkFlag;
    }
}
