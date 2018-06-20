package cts.phase3.persistence.dao;

import cts.phase3.persistence.model.WorkerPicture;
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
public interface WorkerPictureDao {

    int insert(WorkerPicture workerPicture);

    int deleteById(Integer id);

    int update(WorkerPicture workerPicture);

    WorkerPicture findById(Integer id);

    WorkerPicture findByName(String name);

    List<WorkerPicture> findByWorkerName(String name);

    List<WorkerPicture> findByMissionName(String name);

    List<WorkerPicture> findByWorkerNameAndMissionName(
            @Param("workername") String workername, @Param("missionname") String missionname);


    List<WorkerPicture> allWorkerPicture();
}
