package cts.phase3.service.impl;

import cts.phase3.persistence.dao.AnnouncerDao;
import cts.phase3.persistence.dao.ReleaseDao;
import cts.phase3.persistence.model.Announcer;
import cts.phase3.service.AnnouncerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

/**
 * @program: phase3
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-05-31 17:21
 **/
@Service("announcerService")
public class AnnouncerServiceImpl implements AnnouncerService {

    @Resource
    private AnnouncerDao announcerDao;

    @Resource
    private ReleaseDao releaseDao;


    @Override
    public Announcer getAnnouncerById(int userId) {
        return announcerDao.findById(userId);
    }

    @Override
    public boolean addAnnouncer(Announcer record) {
        boolean result = false;
        try {
            announcerDao.insert(record);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean deleteAnnouncer(int id) {
        boolean result = false;
        try {
            announcerDao.deleteById(id);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean updateById(Announcer record) {
        boolean result = false;
        try {
            announcerDao.updateById(record);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<Announcer> allAnnouncer() {
        return announcerDao.allAnnouncer();
    }

    @Override
    public Announcer getAnnouncerByName(String name) {
        return announcerDao.findByName(name);
    }
}
