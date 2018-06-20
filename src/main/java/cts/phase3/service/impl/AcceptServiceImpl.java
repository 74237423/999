package cts.phase3.service.impl;

import cts.phase3.persistence.dao.AcceptDao;
import cts.phase3.persistence.dao.MissionDao;
import cts.phase3.persistence.dao.MissionPictureDao;
import cts.phase3.persistence.dao.WorkerPictureDao;
import cts.phase3.persistence.model.Accept;
import cts.phase3.persistence.model.Mission;
import cts.phase3.persistence.model.MissionPicture;
import cts.phase3.persistence.model.WorkerPicture;
import cts.phase3.service.AcceptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("acceptService")
public class AcceptServiceImpl implements AcceptService {

    @Resource
    private AcceptDao acceptDao;

    @Resource
    private MissionDao missionDao;

    @Resource
    private WorkerPictureDao workerPictureDao;

    @Resource
    private MissionPictureDao missionPictureDao;

    /*
        insert accept，同时把missionPicture复制成missionPicture
     */
    @Override
    public boolean addAccept(Accept accept) {
        boolean result = false;
        try {
            acceptDao.insert(accept);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        String workerName = accept.getWorkerName();
        String missionName = accept.getMissionName();

        List<MissionPicture> missionPictures = this.missionPictureDao.findByMissionName(missionName);
        for (MissionPicture m : missionPictures) {
            WorkerPicture workerPicture = new WorkerPicture();
            workerPicture.setName(m.getName());
            workerPicture.setPicture(m.getPicture());
            workerPicture.setWorkername(workerName);
            workerPicture.setMissionName(missionName);
            this.workerPictureDao.insert(workerPicture);
        }

        return result;
    }

    /*
        根据任务名和用户名获取accept
     */
    @Override
    public Accept getAcceptByMissionNameAndWorkerName(String missionName, String workerName) {
        return acceptDao.findByMissionNameAndWorkerName(workerName, missionName);
    }

    /*
        删除accept的同时删除missionPicture
     */
    @Override
    public boolean deleteAccept(Accept accept) {
        boolean result = false;
        try {
            acceptDao.deleteById(accept.getId());
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /*
        更新accept
     */
    @Override
    public boolean updateAccept(Accept accept) {
        boolean result = false;
        try {
            acceptDao.update(accept);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /*
        获取工人接受的所有任务
     */
    @Override
    public List<Accept> getAllAccept(String workerName) {
        return this.acceptDao.findByWorkerName(workerName);
    }

    /*
        根据accepts返回missions
     */
    @Override
    public List<Mission> getMissionsByWorkerName(String workername) {
        List<Mission> ret = new ArrayList<>();
        List<String> missionNames = new ArrayList<>();
        List<Accept> acceptList = this.acceptDao.findByWorkerName(workername);

        for (Accept a : acceptList) {
            missionNames.add(a.getMissionName());
        }

        for (String s : missionNames) {
            Mission mission = missionDao.findByName(s);
            ret.add(mission);
        }
        return ret;
    }

    @Override
    public List<Accept> getAcceptByMissionName(String missionName) {
        return null;
    }
}
