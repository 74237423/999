package cts.phase3.persistence.dao;

import cts.phase3.dataenum.Type;
import cts.phase3.persistence.model.Mission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @program: phase3
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-05-28 22:55
 **/
@Mapper
public interface MissionDao {

    int deleteById(Integer id);

    int insert(Mission record);

    int insertSelective(Mission record);

    Mission findById(Integer id);

    Mission findByName(String name);

    List<Mission> findByType(String type);

    List<Mission> findByDifficultyClass(String difficultyClass);

    Mission findByEndTime(String endtime);

    int updateByPrimaryKeySelective(Mission record);

    int updateById(Mission record);

    List<Mission> allMission();

}
