package cts.phase3.persistence.model;

import cts.phase3.dataenum.State;

import java.io.Serializable;

/**
 * @program: CTS2018_Phase_III
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-06-03 13:15
 **/
public class Accept implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String workerName;

    private String missionName;

    private String start;

    private String end;

    private int isFinished;


    private int checkFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }




    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }


    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }




    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }


    public int getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(int isFinished) {
        this.isFinished = isFinished;
    }

    public int getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(int checkFlag) {
        this.checkFlag = checkFlag;
    }
}
