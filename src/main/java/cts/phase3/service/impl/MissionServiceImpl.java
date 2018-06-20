package cts.phase3.service.impl;

import cts.phase3.dataenum.Type;
import cts.phase3.persistence.dao.MissionDao;
import cts.phase3.persistence.model.Mission;
import cts.phase3.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

/**
 * @program: phase3
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-05-29 10:19
 **/
@Service("missionService")
public class MissionServiceImpl implements MissionService {

    @Resource
    private MissionDao missionDao;

    public Mission getMissionById(int userId) {
        return missionDao.findById(userId);
    }

    @Override
    public Mission getMissionByName(String name) {
        return missionDao.findByName(name);
    }

    public boolean addMission(Mission record){
        boolean result = false;
        try {
            missionDao.insert(record);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean deleteMission(int id) {
        boolean result = false;
        try {
            missionDao.deleteById(id);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean updateById(Mission record) {
        boolean result = false;
        try {
            missionDao.updateById(record);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<Mission> allMission() {
        return missionDao.allMission();
    }

    @Override
    public Mission getMissionByEndTime(String endtime) {
        return missionDao.findByEndTime(endtime);
    }

    @Override
    public List<Mission> getMissionByType(String type) {
        return this.missionDao.findByType(type);
    }
}
