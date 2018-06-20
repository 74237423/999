package cts.phase3.service;

import cts.phase3.persistence.model.Accept;
import cts.phase3.persistence.model.Mission;

import java.util.List;

public interface AcceptService {

    public boolean addAccept(Accept accept);

    public Accept getAcceptByMissionNameAndWorkerName(String missionName, String workerName);

    public boolean deleteAccept(Accept accept);

    public boolean updateAccept(Accept accept);

    public List<Accept> getAllAccept(String workerName);

    public List<Mission>  getMissionsByWorkerName(String workerName);

    public List<Accept> getAcceptByMissionName(String missionName);

}
