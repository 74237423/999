package cts.phase3.dao_stub;

import cts.phase3.persistence.dao.WorkerDao;
import cts.phase3.persistence.model.Worker;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: CTS2018_Phase_III
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-05-20 19:17
 **/
public class Worker_Stub implements WorkerDao {


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Worker record) {
        return 0;
    }

    @Override
    public int insertSelective(Worker record) {
        return 0;
    }

    @Override
    public Worker findById(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Worker record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Worker record) {
        return 0;
    }

    @Override
    public List<Worker> allWorker() {
        return null;
    }

    @Override
    public Worker findByName(String name) {
        return null;
    }
}
