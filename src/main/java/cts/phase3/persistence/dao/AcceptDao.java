package cts.phase3.persistence.dao;

import cts.phase3.persistence.model.Accept;
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
public interface AcceptDao {

    int insert(Accept accept);

    int deleteById(Integer id);

    int update(Accept accept);

    Accept findById(Integer id);

    List<Accept> findByMissionName(String missionName);

    List<Accept> findByWorkerName(String workerName);

    Accept findByMissionNameAndWorkerName(
            @Param("workerName") String workerName, @Param("missionName") String missionName);

    List<Accept> allAccept();

}
