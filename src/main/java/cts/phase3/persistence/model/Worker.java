package cts.phase3.persistence.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @program: phase3
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-05-28 21:07
 **/
public class Worker extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    private ArrayList<String> missions;

    public ArrayList<String> getMissions() {
        return missions;
    }

    public Worker(){
        points = 0;

    }

    public void setMissions(ArrayList<String> missions) {
        this.missions = missions;
    }
}
