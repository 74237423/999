package cts.phase3.dao_stub;



import cts.phase3.persistence.dao.RaterDao;
import cts.phase3.persistence.model.Rater;

import java.util.List;

/**
 * @program: CTS2018_Phase_III
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-05-22 11:23
 **/
public class Rater_Stub implements RaterDao {

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Rater record) {
        return 0;
    }

    @Override
    public int insertSelective(Rater record) {
        return 0;
    }

    @Override
    public Rater findById(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Rater record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Rater record) {
        return 0;
    }

    @Override
    public List<Rater> allRater() {
        return null;
    }

    @Override
    public Rater findByName(String name) {
        return null;
    }
}
