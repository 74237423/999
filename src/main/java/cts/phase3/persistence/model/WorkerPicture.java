package cts.phase3.persistence.model;

import java.io.Serializable;

/**
 * @program: CTS2018_Phase_III
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-06-03 13:43
 **/
public class WorkerPicture implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String workername;

    private String missionname;

    private byte[] picture;

    private String tag;

    private int state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getMissionName() {
        return missionname;
    }

    public void setMissionName(String missionname) {
        this.missionname = missionname;
    }

    public String getWorkername() {
        return workername;
    }

    public void setWorkername(String workername) {
        this.workername = workername;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
