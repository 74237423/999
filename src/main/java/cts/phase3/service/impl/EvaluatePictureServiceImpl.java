package cts.phase3.service.impl;

import cts.phase3.persistence.dao.EvaluatePictureDao;
import cts.phase3.persistence.model.EvaluatePicture;
import cts.phase3.service.EvaluatePictureService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("evaluatePictureService")
public class EvaluatePictureServiceImpl implements EvaluatePictureService {

    @Resource
    private EvaluatePictureDao evaluatePictureDao;

    @Override
    public boolean addEvaluatePicture(EvaluatePicture evaluatePicture) {
        boolean result = false;
        try {
            evaluatePictureDao.insert(evaluatePicture);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean deleteEvaluatePicture(EvaluatePicture evaluatePicture) {
        boolean result = false;
        try {
            evaluatePictureDao.delete(evaluatePicture.getId());
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateEvaluatePicture(EvaluatePicture evaluatePicture) {
        boolean result = false;
        try {
            evaluatePictureDao.update(evaluatePicture);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<EvaluatePicture> selectEvaluatePictureByRaterAndMission(String raterName, String missionName) {
        return this.evaluatePictureDao.findEvaluatePictureByRaterAndMission(raterName, missionName);
    }
}
