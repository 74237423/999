package cts.phase3.service.impl;

import cts.phase3.persistence.dao.AcceptDao;
import cts.phase3.persistence.dao.WorkerDao;
import cts.phase3.persistence.model.Accept;
import cts.phase3.persistence.model.Worker;
import cts.phase3.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: phase3
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-05-31 17:18
 **/
@Service("workerService")
public class WorkerServiceImpl implements WorkerService {

    @Resource
    private WorkerDao workerDao;

    @Resource
    private AcceptDao acceptDao;

    @Override
    public Worker getWorkerById(int userId) {
        Worker worker = workerDao.findById(userId);
        ArrayList<String> missions = getMission(worker.getUsername());
        worker.setMissions(missions);
        return worker;
    }

    @Override
    public Worker getWorkerByName(String name) {
        return workerDao.findByName(name);
    }

    @Override
    public boolean addWorker(Worker record) {
        boolean result = false;
        try {
            workerDao.insert(record);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean deleteWorker(int id) {
        boolean result = false;
        try {
            workerDao.deleteByPrimaryKey(id);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean updateById(Worker record) {
        boolean result = false;
        try {
            workerDao.updateByPrimaryKey(record);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<Worker> allWorker() {
        return this.workerDao.allWorker();
    }

    @Override
    public List<String> getAllMissions(String workername) {
        ArrayList<String> missions = new ArrayList<>();
        List<Accept> acceptList = this.acceptDao.findByWorkerName(workername);
        for (Accept a : acceptList) {
            missions.add(a.getMissionName());
        }
        return missions;
    }

    @Override
    public Accept findByMissionNameAndWorkerName(String workerName, String missionName) {
        return this.acceptDao.findByMissionNameAndWorkerName(workerName, missionName);
    }

    public ArrayList<String> getMission(String workerName) {
        List<Accept> accepts = this.acceptDao.findByWorkerName(workerName);
        ArrayList<String> missionNames = new ArrayList<>();
        for (Accept a: accepts) {
            missionNames.add(a.getMissionName());
        }
        return  missionNames;
    }
}
