package cts.phase3.persistence.dao;

import cts.phase3.persistence.model.Release;
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
public interface ReleaseDao {

    int insert(Release release);

    int deleteById(Integer id);

    int update(Release release);

    Release findById(Integer id);

    Release findByMissionName(String name);

    List<Release> findByAnnouncerName(String name);

    List<Release> findByAnnouncerNameAndMissionName(
            @Param("announcerName") String announcerName, @Param("missionName") String missionName);

    List<Release> allRelease();
}
