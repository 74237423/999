package cts.phase3.persistence.dao;

import cts.phase3.persistence.model.MissionPicture;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @program: CTS2018_Phase_III
 * @author: Lijie
 * @description: ${description}
 * @create:
 **/
@Mapper
public interface MissionPictureDao {

    int insert(MissionPicture missionPicture);

    int deleteById(Integer id);

    int deleteByMissionName(String missionName);

    int update(MissionPicture missionPicture);

    MissionPicture findById(Integer id);

    MissionPicture findByName(String name);

    List<MissionPicture> findByMissionName(String name);

    List<MissionPicture> allMissionPicture();


}
