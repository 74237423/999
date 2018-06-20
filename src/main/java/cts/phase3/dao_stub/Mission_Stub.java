package cts.phase3.dao_stub;


import cts.phase3.persistence.dao.MissionDao;
import cts.phase3.persistence.model.Mission;

import java.util.List;

/**
 * @program: CTS2018_Phase_III
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-05-22 11:07
 **/
public class Mission_Stub implements MissionDao {


    @Override
    public int deleteById(Integer id) {
        return 0;
    }

    @Override
    public int insert(Mission record) {
        return 0;
    }

    @Override
    public int insertSelective(Mission record) {
        return 0;
    }

    @Override
    public Mission findById(Integer id) {
        return null;
    }

    @Override
    public Mission findByName(String name) {
        return null;
    }

    @Override
    public List<Mission> findByType(String type) {
        return null;
    }

    @Override
    public List<Mission> findByDifficultyClass(String difficultyClass) {
        return null;
    }

    @Override
    public Mission findByEndTime(String endtime) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Mission record) {
        return 0;
    }

    @Override
    public int updateById(Mission record) {
        return 0;
    }

    @Override
    public List<Mission> allMission() {
        return null;
    }
}
