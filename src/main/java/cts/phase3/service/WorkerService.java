package cts.phase3.service;

import cts.phase3.persistence.model.Accept;
import cts.phase3.persistence.model.Mission;
import cts.phase3.persistence.model.Worker;

import java.util.List;

/**
 * @program: phase3
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-05-31 17:29
 **/
public interface WorkerService {

    public Worker getWorkerById(int userId);

    public Worker getWorkerByName(String name);

    boolean addWorker(Worker record);

    public boolean deleteWorker(int id);

    public boolean updateById(Worker record);

    public List<Worker> allWorker();

    List<String> getAllMissions(String workername);

    Accept findByMissionNameAndWorkerName(String workerName, String missionName);

}
