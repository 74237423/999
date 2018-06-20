package cts.phase3.persistence.dao;

import cts.phase3.persistence.model.EvaluatePicture;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: CTS2018_Phase_III
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-06-17 16:04
 **/
@Mapper
public interface EvaluatePictureDao {
    int insert(EvaluatePicture evaluatePicture);

    int delete(Integer id);

    int update(EvaluatePicture evaluatePicture);

    List<EvaluatePicture> findEvaluatePictureByRaterAndMission(
            @Param("ratername") String ratername, @Param("missionname") String missionname);
}
