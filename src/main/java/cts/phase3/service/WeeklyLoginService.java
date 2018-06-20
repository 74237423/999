package cts.phase3.service;

import cts.phase3.persistence.model.Weekly;

/**
 * @program: CTS2018_Phase_III
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-06-17 22:56
 **/
public interface WeeklyLoginService {

    boolean addWeeklyLogin(Weekly weekly);

    boolean updateWeeklyLogin(Weekly weekly);

    Weekly findById(Integer id);
}
