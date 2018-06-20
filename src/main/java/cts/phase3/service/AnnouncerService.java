package cts.phase3.service;

import cts.phase3.persistence.model.Announcer;

import java.util.List;

/**
 * @program: phase3
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-05-31 17:26
 **/
public interface AnnouncerService {

    public Announcer getAnnouncerById(int userId);

    boolean addAnnouncer(Announcer record);

    public boolean deleteAnnouncer(int id);

    public boolean updateById(Announcer record);

    public List<Announcer> allAnnouncer();

    public Announcer getAnnouncerByName(String name);
}
