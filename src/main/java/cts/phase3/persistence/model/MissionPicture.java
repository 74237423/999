package cts.phase3.persistence.model;

import java.io.Serializable;

/**
 * @program: phase3
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-05-30 23:55
 **/
public class MissionPicture implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String missionName;

    private byte[] picture;



    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
