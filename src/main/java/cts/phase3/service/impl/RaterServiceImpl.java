package cts.phase3.service.impl;

import cts.phase3.persistence.dao.EvaluateDao;
import cts.phase3.persistence.dao.RaterDao;
import cts.phase3.persistence.model.Rater;
import cts.phase3.service.RaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

/**
 * @program: phase3
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-05-31 17:19
 **/
@Service("raterService")
public class RaterServiceImpl implements RaterService {

    @Resource
    private RaterDao raterDao;

    @Resource
    private EvaluateDao evaluateDao;

    @Override
    public Rater getRaterById(int userId) {
        return raterDao.findById(userId);
    }

    @Override
    public Rater getRaterByName(String name) {
        return raterDao.findByName(name);
    }

    @Override
    public boolean addRater(Rater record) {
        boolean result = false;
        try {
            raterDao.insert(record);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean deleteRater(int id) {
        boolean result = false;
        try {
            raterDao.deleteByPrimaryKey(id);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean updateById(Rater record) {
        boolean result = false;
        try {
            raterDao.updateByPrimaryKey(record);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<Rater> allRater() {
        return raterDao.allRater();
    }
}
