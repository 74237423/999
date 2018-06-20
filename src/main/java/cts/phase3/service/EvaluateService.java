package cts.phase3.service;

import cts.phase3.persistence.model.*;
import org.hibernate.jdbc.Work;

import java.util.List;

public interface EvaluateService {

    public boolean addEvaluate(Evaluate evaluate);

    public boolean updateEvaluate(Evaluate evaluate);

    public boolean deleteEvaluate(Evaluate evaluate);

    public Evaluate selectEvaluateByRaterAndMission(String raterName, String missionName);

    public List<Evaluate> getAllEvaluateByRater(String ratername);

    public Mission getAllMissionsByWorkerAndMission(Worker worker, Mission mission);

    public List<WorkerPicture> getAllWorkerPictureByAccept(Accept accept);

    Evaluate getEvaluateByMission(String missionName);

    Accept getAcceptByEvaluate(String name);

    List<Evaluate> getAllEvaluates();
}
