package cts.phase3.persistence.dao;

import cts.phase3.persistence.model.Weekly;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: CTS2018_Phase_III
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-06-17 22:40
 **/
@Mapper
public interface WeeklyDao {
    int update(Weekly weekly);

    int insert(Weekly weekly);

    Weekly findById(Integer id);
}
