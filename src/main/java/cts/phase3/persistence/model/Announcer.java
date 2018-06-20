package cts.phase3.persistence.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @program: phase3
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-05-28 23:05
 **/
public class Announcer extends User implements Serializable {
    private static final long serialVersionUID = 1L;

    public Announcer(){
        points = 0;
    }

    private ArrayList<String> missions;

    public ArrayList<String> getMissions() {
        return missions;
    }

    public void setMissions(ArrayList<String> missions) {
        this.missions = missions;
    }
}
