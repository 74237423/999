package cts.phase3.service.impl;

import cts.phase3.persistence.dao.WeeklyDao;
import cts.phase3.persistence.model.Weekly;
import cts.phase3.service.WeeklyLoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: CTS2018_Phase_III
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-06-17 22:58
 **/
@Service("weeklyLoginService")
public class WeeklyLoginServiceImpl implements WeeklyLoginService {

    @Resource
    private WeeklyDao weeklyLoginDao;

    @Override
    public boolean addWeeklyLogin(Weekly weekly) {
        boolean result = false;
        try {
            this.weeklyLoginDao.insert(weekly);
            result = true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateWeeklyLogin(Weekly weekly) {
        boolean result = false;
        try {
            this.weeklyLoginDao.update(weekly);
            result = true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Weekly findById(Integer id) {
        return this.weeklyLoginDao.findById(id);
    }

}
