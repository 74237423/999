package cts.phase3.persistence.dao;

import cts.phase3.persistence.model.Rater;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @program: phase3
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-05-28 22:54
 **/
@Mapper
public interface RaterDao {

    int deleteByPrimaryKey(Integer id);

    int insert(Rater record);

    int insertSelective(Rater record);

    Rater findById(Integer id);

    int updateByPrimaryKeySelective(Rater record);

    int updateByPrimaryKey(Rater record);

    List<Rater> allRater();

    Rater findByName(String name);
}
