package cts.phase3.service;

import cts.phase3.persistence.model.Mission;
import cts.phase3.persistence.model.Rater;

import java.util.List;

/**
 * @program: phase3
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-05-31 17:27
 **/
public interface RaterService {
    public Rater getRaterById(int userId);

    public Rater getRaterByName(String name);

    boolean addRater(Rater record);

    public boolean deleteRater(int id);

    public boolean updateById(Rater record);

    public List<Rater> allRater();
}
