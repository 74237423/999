package cts.phase3.service.impl;

import cts.phase3.persistence.dao.WorkerPictureDao;
import cts.phase3.persistence.model.Accept;
import cts.phase3.persistence.model.WorkerPicture;
import cts.phase3.service.WorkerPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

@Service("workerPictureService")
public class WorkerPictureServiceImpl implements WorkerPictureService {


    @Resource
    private WorkerPictureDao workerPictureDao;

    @Override
    public List<WorkerPicture> selectWorkerPictureByAccept(Accept accept) {

        return this.workerPictureDao.findByWorkerNameAndMissionName(accept.getWorkerName(), accept.getMissionName());
    }

    @Override
    public boolean updateWorkerPictureByName(WorkerPicture workerPicture) {
        boolean result = false;
        try {
            workerPictureDao.update(workerPicture);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<WorkerPicture> findByWorkerNameAndMissionName(String workerName, String missionName) {
        return this.workerPictureDao.findByWorkerNameAndMissionName(workerName, missionName);
    }
}
