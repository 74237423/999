package cts.phase3.service;

import cts.phase3.dataenum.Type;
import cts.phase3.persistence.model.Mission;

import java.util.List;

/**
 * @program: phase3
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-05-28 23:30
 **/
public interface MissionService {

    public Mission getMissionById(int userId);

    public Mission getMissionByName(String name);

    boolean addMission(Mission record);

    public boolean deleteMission(int id);

    public boolean updateById(Mission record);

    public List<Mission> allMission();

    public Mission getMissionByEndTime(String endtime);

    public List<Mission> getMissionByType(String type);



}

