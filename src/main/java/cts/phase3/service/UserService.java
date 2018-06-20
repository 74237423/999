package cts.phase3.service;

import cts.phase3.persistence.model.User;

/**
 * @program: phase3
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-05-28 21:09
 **/
public interface UserService {
    public User getUserById(int userId);

    boolean addUser(User record);
}
