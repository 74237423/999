package cts.phase3.persistence.dao;

import cts.phase3.persistence.model.Evaluate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: CTS2018_Phase_III
 * @author: Lijie
 * @description: ${description}
 * @create:
 **/
@Mapper
public interface EvaluateDao {

    int insert(Evaluate evaluate);

    int deleteById(Integer id);

    int update(Evaluate evaluate);

    Evaluate findById(Integer id);

    List<Evaluate> findByRaterName(String raterName);

    Evaluate findByMissionName(String missionName);

    List<Evaluate> allEvaluate();

    Evaluate findEvaluateByRaterAndMission(
            @Param("raterName") String raterName, @Param("missionName") String missionName);
}
