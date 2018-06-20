package cts.phase3.persistence.dao;

import cts.phase3.persistence.model.Announcer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @program: phase3
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-05-28 22:53
 **/
@Mapper
public interface AnnouncerDao {

    int deleteById(Integer id);

    int insert(Announcer record);

    int insertSelective(Announcer record);

    Announcer findById(Integer id);

    int updateByPrimaryKeySelective(Announcer record);

    int updateById(Announcer record);

    List<Announcer> allAnnouncer();

    Announcer findByName(String name);
}
