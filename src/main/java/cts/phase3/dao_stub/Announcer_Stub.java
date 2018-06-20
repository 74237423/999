package cts.phase3.dao_stub;


import cts.phase3.persistence.dao.AnnouncerDao;
import cts.phase3.persistence.model.Announcer;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: CTS2018_Phase_III
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-05-22 11:16
 **/
public class Announcer_Stub implements AnnouncerDao {


    @Override
    public int deleteById(Integer id) {
        return 0;
    }

    @Override
    public int insert(Announcer record) {
        return 0;
    }

    @Override
    public int insertSelective(Announcer record) {
        return 0;
    }

    @Override
    public Announcer findById(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Announcer record) {
        return 0;
    }

    @Override
    public int updateById(Announcer record) {
        return 0;
    }

    @Override
    public List<Announcer> allAnnouncer() {
        return null;
    }

    @Override
    public Announcer findByName(String name) {
        return null;
    }
}
