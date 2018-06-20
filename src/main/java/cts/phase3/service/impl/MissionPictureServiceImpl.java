package cts.phase3.service.impl;

import cts.phase3.persistence.dao.MissionPictureDao;
import cts.phase3.persistence.model.MissionPicture;
import cts.phase3.service.MissionPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("missionPictureService")
public class MissionPictureServiceImpl implements MissionPictureService {

    @Resource
    private MissionPictureDao missionPictureDao;

    @Override
    public boolean addMissionPicture(MissionPicture missionPicture) {
        boolean result = false;
        try {
            missionPictureDao.insert(missionPicture);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean deleteMissionPicture(MissionPicture missionPicture) {
        boolean result = false;
        try {
            missionPictureDao.deleteById(missionPicture.getId());
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<MissionPicture> selectMissionPicturesByMission(String missionName) {
        List<MissionPicture> pictureList = this.missionPictureDao.findByMissionName(missionName);
        return pictureList;
    }

    @Override
    public boolean insertArray(ArrayList<MissionPicture> pictures) {
        boolean result = false;
        try {
            for (MissionPicture mp : pictures) {
                this.missionPictureDao.insert(mp);
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
