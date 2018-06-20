package cts.phase3.service.impl;

import cts.phase3.persistence.dao.EvaluateDao;
import cts.phase3.persistence.dao.MissionDao;
import cts.phase3.persistence.dao.RaterDao;
import cts.phase3.persistence.dao.WorkerPictureDao;
import cts.phase3.persistence.model.*;
import cts.phase3.service.EvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("evaluateService")
public class EvaluateServiceImpl implements EvaluateService {

    @Resource
    private EvaluateDao evaluateDao;

    @Resource
    private RaterDao raterDao;

    @Resource
    private MissionDao missionDao;

    @Resource
    private WorkerPictureDao workerPictureDao;

    /*
        新增Eva
     */
    @Override
    public boolean addEvaluate(Evaluate evaluate) {
        boolean result = false;
        try {
            evaluateDao.insert(evaluate);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /*
        更新Eva
     */
    @Override
    public boolean updateEvaluate(Evaluate evaluate) {
        boolean result = false;
        try {
            evaluateDao.update(evaluate);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /*
        删除Eva
     */
    @Override
    public boolean deleteEvaluate(Evaluate evaluate) {
        boolean result = false;
        try {
            evaluateDao.deleteById(evaluate.getId());
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /*
        根据rater名和mission名返回Eva
     */
    @Override
    public Evaluate selectEvaluateByRaterAndMission(String raterName, String missionName) {
        return evaluateDao.findEvaluateByRaterAndMission(raterName, missionName);
    }

    /*
        返回Rater的所有Eva
     */
    @Override
    public List<Evaluate> getAllEvaluateByRater(String ratername) {
        List<Evaluate> list = this.evaluateDao.findByRaterName(ratername);
        return list;
    }

    /*
        返回所有的mission，mission的名字格式为workerName_missionName
     */
    @Override
    public Mission getAllMissionsByWorkerAndMission(Worker worker, Mission mission) {
        StringBuilder sb = new StringBuilder(worker.getUsername());
        sb.append("_");
        sb.append(mission.getName());
        Mission ret = this.missionDao.findByName(sb.toString());
        return ret;
    }

    /*
        返回accept的所有workerPicture
     */
    @Override
    public List<WorkerPicture> getAllWorkerPictureByAccept(Accept accept) {
        return this.workerPictureDao.findByWorkerNameAndMissionName(accept.getWorkerName(), accept.getMissionName());
    }

    @Override
    public Evaluate getEvaluateByMission(String missionName) {
        return this.evaluateDao.findByMissionName(missionName);

    }
    @Override
    public Accept getAcceptByEvaluate(String name) {
        return null;
    }

    @Override
    public List<Evaluate> getAllEvaluates() {
        return this.evaluateDao.allEvaluate();
    }
}
