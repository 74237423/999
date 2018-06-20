package cts.phase3.persistence.model;

import cts.phase3.dataenum.Type;

import java.io.Serializable;

/**
 * @program: phase_3
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-05-25 19:47
 **/
public class Mission implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    //任务名
    private String name;
    //开始时间
    private String start;
    //结束时间
    private String end;
    //任务奖励积分
    private Integer points;
    //任务所需工人
    private Integer needs;

    //任务接受人数
    private Integer accepts;

    //任务描述
    private String description;

    // 任务类型
    private String type;

    // 任务方式
    private String way;

    // 任务等级
    private String difficultyClass;

    private byte[] pictures;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNeeds() {
        return needs;
    }

    public void setNeeds(int needs) {
        this.needs = needs;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getPictures() {
        return pictures;
    }

    public void setPictures(byte[] pictures) {
        this.pictures = pictures;
    }

    public String getDifficultyClass() {
        return difficultyClass;
    }

    public void setDifficultyClass(String difficultyClass) {
        this.difficultyClass = difficultyClass;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAccepts() {
        return accepts;
    }

    public void setAccepts(Integer accepts) {
        this.accepts = accepts;
    }
}
