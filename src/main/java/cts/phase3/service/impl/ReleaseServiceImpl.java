package cts.phase3.service.impl;

import cts.phase3.persistence.dao.AnnouncerDao;
import cts.phase3.persistence.dao.MissionDao;
import cts.phase3.persistence.dao.ReleaseDao;
import cts.phase3.persistence.model.Mission;
import cts.phase3.persistence.model.Release;
import cts.phase3.service.ReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("releaseService")
public class ReleaseServiceImpl implements ReleaseService {

    @Resource
    private ReleaseDao releaseDao;

    @Resource
    private MissionDao missionDao;

    @Resource
    private AnnouncerDao announcerDao;

    @Override
    public boolean addRelease(Release release) {
        boolean result = false;
        try {
            releaseDao.insert(release);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean deleteRelease(Release release) {
        boolean result = false;
        try {
            releaseDao.deleteById(release.getId());
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<Release> selectRelease(String missionName, String announcerName) {
        return this.releaseDao.findByAnnouncerNameAndMissionName(announcerName, missionName);
    }

    @Override
    public List<Release> getAllReleaseByAnnouncer(String announcerName) {
        return this.releaseDao.findByAnnouncerName(announcerName);
    }

    @Override
    public List<Mission> getMissionByAnnouncerName(String name) {
        List<Release> missions = this.releaseDao.findByAnnouncerName(name);
        ArrayList<String> list = new ArrayList<>();
        List<Mission> result = new ArrayList<>();
        for (Release r : missions) {
            list.add(r.getMissionName());
        }

        for (String s : list) {
            Mission m = this.missionDao.findByName(s);
            result.add(m);
        }
        return result;
    }

    @Override
    public Release selectReleaseByMissionName(String name) {
        return this.releaseDao.findByMissionName(name);
    }

    @Override
    public boolean updateRelease(Release release) {
        boolean result = false;
        try {
            releaseDao.update(release);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


}
