package cts.phase3.persistence.model;

import java.io.Serializable;

public class EvaluatePicture implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String ratername;

    private String missionname;

    private int state;

    private int isRight;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRatername() {
        return ratername;
    }

    public void setRatername(String ratername) {
        this.ratername = ratername;
    }

    public String getMissionname() {
        return missionname;
    }

    public void setMissionname(String missionname) {
        this.missionname = missionname;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getIsRight() {
        return isRight;
    }

    public void setIsRight(int isRight) {
        this.isRight = isRight;
    }
}
