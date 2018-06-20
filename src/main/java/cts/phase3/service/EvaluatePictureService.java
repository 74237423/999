package cts.phase3.service;

import cts.phase3.persistence.model.EvaluatePicture;

import java.util.List;

public interface EvaluatePictureService {

    public boolean addEvaluatePicture(EvaluatePicture evaluatePicture);

    public boolean deleteEvaluatePicture(EvaluatePicture evaluatePicture);

    public boolean updateEvaluatePicture(EvaluatePicture evaluatePicture);

    public List<EvaluatePicture> selectEvaluatePictureByRaterAndMission(String raterName, String missionName);
}
