package cts.phase3.service;

import cts.phase3.persistence.model.Accept;
import cts.phase3.persistence.model.WorkerPicture;

import java.util.List;

public interface WorkerPictureService {

    public List<WorkerPicture> selectWorkerPictureByAccept(Accept accept);

    public boolean updateWorkerPictureByName(WorkerPicture workerPicture);

    List<WorkerPicture> findByWorkerNameAndMissionName(String workerName, String missionName);

}
