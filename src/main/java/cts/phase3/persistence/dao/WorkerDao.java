package cts.phase3.persistence.dao;

import cts.phase3.persistence.model.Worker;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @program: phase3
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-05-28 22:52
 **/
@Mapper
public interface WorkerDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Worker record);

    int insertSelective(Worker record);

    Worker findById(Integer id);

    int updateByPrimaryKeySelective(Worker record);

    int updateByPrimaryKey(Worker record);

    List<Worker> allWorker();

    Worker findByName(String name);
}
