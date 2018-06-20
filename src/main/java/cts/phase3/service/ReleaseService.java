package cts.phase3.service;

import cts.phase3.persistence.model.Mission;
import cts.phase3.persistence.model.Release;

import java.util.ArrayList;
import java.util.List;

public interface ReleaseService {

    public boolean addRelease(Release release);

    public boolean deleteRelease(Release release);

    public List<Release> selectRelease(String missionName, String announcerName);

    public List<Release> getAllReleaseByAnnouncer(String announcerName);

    public List<Mission> getMissionByAnnouncerName(String name);

    public Release selectReleaseByMissionName(String name);

    public boolean updateRelease(Release release);
}
