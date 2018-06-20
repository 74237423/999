package cts.phase3.service;

import cts.phase3.persistence.model.MissionPicture;

import java.util.ArrayList;
import java.util.List;

public interface MissionPictureService {

    public boolean addMissionPicture(MissionPicture missionPicture);

    public boolean deleteMissionPicture(MissionPicture missionPicture);

    public List<MissionPicture> selectMissionPicturesByMission(String missionName);

    public boolean insertArray(ArrayList<MissionPicture> pictures);

}
