package cts.phase3.controller;

import cts.phase3.controller.utils.MissionUtil;
import cts.phase3.controller.utils.UploadUtil;
import cts.phase3.controller.utils.WeeklyUtil;
import cts.phase3.persistence.model.*;
import cts.phase3.service.*;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @program: phase3
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-05-31 19:15
 **/
@Controller
@RequestMapping("/{username}")
public class AnnouncerController {
    @Resource
    private AnnouncerService announcerService;

    @Resource
    private MissionService missionService;

    @Resource
    private ReleaseService releaseService;

    @Resource
    private MissionPictureService missionPictureService;

    @Resource
    private WorkerPictureService workerPictureService;

    @Resource
    private RaterService raterService;

    @Resource
    private WeeklyLoginService weeklyLoginService;

    @Resource
    private AcceptService acceptService;

    @Resource
    private EvaluateService evaluateService;

    @Resource
    private EvaluatePictureService evaluatePictureService;

    @RequestMapping(value = "/release", method = POST)
    @ResponseBody
    public String release(@PathVariable("username") String username,
                          String missionName, String startTime, String endTime, String points, String needs,
                          String way, String description, MultipartFile file, String category, String difficultyClass){
        Mission oldMission = missionService.getMissionByName(missionName);
        if(oldMission != null)
            return "重复";
        Mission mission = new Mission();
        mission.setName(missionName);
        mission.setPoints(Integer.parseInt(points));
        mission.setNeeds(Integer.parseInt(needs));
        mission.setWay(way);
        mission.setDescription(description);
        mission.setType(category);
        mission.setDifficultyClass(difficultyClass);
        mission.setAccepts(0);
        String[] starts = startTime.split("-");
        if(starts[1].length() == 1)
            starts[1] = "0" + starts[1];
        String start = starts[0] + starts[1] + starts[2];
        mission.setStart(start);
        String[] ends = endTime.split("-");
        if(ends[1].length() == 1)
            ends[1] = "0" + ends[1];
        String end = ends[0] + ends[1] + ends[2];
        mission.setEnd(end);

        Release release = new Release();
        release.setMissionName(missionName);
        release.setAnnouncerName(username);
        release.setStart(start);
        release.setEnd(end);

        missionService.addMission(mission);
        releaseService.addRelease(release);
        try {
            FileUtils.writeByteArrayToFile(new File(file.getOriginalFilename()),file.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }

        UploadUtil uploadUtil = new UploadUtil();
        System.out.println(uploadUtil.uploadFile(file, missionName, username, missionPictureService));

        WeeklyUtil weeklyUtil = new WeeklyUtil();
        weeklyUtil.updateWeekly(4, weeklyLoginService);

        return "Success";
    }

    @RequestMapping(value = "/load/released", method = GET)
    @ResponseBody
    public String loadReleased(@PathVariable("username") String username){
        List<Mission> allMission = missionService.allMission();
        List<Release> allRelease = releaseService.getAllReleaseByAnnouncer(username);


        List<Mission> released = releaseService.getMissionByAnnouncerName(username);
        //ArrayList<Mission> missions = new ArrayList<>();
        MissionUtil missionUtil = new MissionUtil();
        return missionUtil.missionsToStr(released, missionPictureService);
    }

    @RequestMapping(value = "/announcerInfo", method = GET)
    @ResponseBody
    public String info(@PathVariable("username") String username){
        Announcer announcer = announcerService.getAnnouncerByName(username);
        int releasedInt = releaseService.getAllReleaseByAnnouncer(announcer.getUsername()).size();
        String result = "{\"username\":\"" + announcer.getUsername() + "\", \"password\":\"" + announcer.getPassword()
                + "\", \"points\":\"" + announcer.getPoints() + "\", \"sex\":\"" + announcer.getSex() + "\", \"area\":\""
                + announcer.getArea() + "\", \"phone\":\"" + announcer.getPhone() + "\", \"email\":\"" + announcer.getEmail()
                + "\", \"numOfReleased\":\"" + releasedInt + "\"}";
        return result;
    }

    @RequestMapping(value = "/updateAnnouncerInfo/{updateStr}", method = POST)
    @ResponseBody
    public String updateInfo(@PathVariable("username") String username, @PathVariable("updateStr") String updateStr){
        Announcer announcer = announcerService.getAnnouncerByName(username);
        String[] str = updateStr.split("_");
        if(!str[0].equals("email")){
            str[0] = str[0].replaceAll(",", ".");
            announcer.setEmail(str[0]);
        }
        if(!str[1].equals("password"))
            announcer.setPassword(str[1]);
        if(!str[2].equals("sex")){
            if(str[2].equals("male"))
                announcer.setSex(0);
            else
                announcer.setSex(1);
        }
        if(!str[3].equals("area"))
            announcer.setArea(str[3]);
        if(!str[4].equals("phone"))
            announcer.setPhone(str[4]);
        announcerService.updateById(announcer);
        return "Success";
    }

    @RequestMapping(value = "/pay/{points}", method = POST)
    @ResponseBody
    public boolean pay(@PathVariable("username") String username, @PathVariable("points") String points){
        Announcer announcer = announcerService.getAnnouncerByName(username);
        Integer p = Integer.parseInt(points);
        announcer.setPoints(announcer.getPoints() + p);
        return announcerService.updateById(announcer);
    }

    @RequestMapping(value = "/viewAllAccepts", method = GET)
    @ResponseBody
    public String viewAllAccepts(@PathVariable("username") String username){
        Announcer announcer = announcerService.getAnnouncerByName(username);
        List<Release> releases = releaseService.getAllReleaseByAnnouncer(username);
        List<Mission> missions = releaseService.getMissionByAnnouncerName(username);
        List<Mission> acceptMissions = new ArrayList<>();
        List<Accept> accepts = new ArrayList<>();
        for(int i = 0; i < missions.size(); i++){
            Mission mission = missions.get(i);
            List<Accept> acceptByMissionName = acceptService.getAcceptByMissionName(mission.getName());
            for(int j = 0; j < acceptByMissionName.size(); j++){
                accepts.add(acceptByMissionName.get(j));
                acceptMissions.add(mission);
            }
        }
        String ret = "";
        for(int i = 0; i < accepts.size(); i++){

            Accept accept = accepts.get(i);
            Mission mission = acceptMissions.get(i);

                List<WorkerPicture> workerPictures = workerPictureService.selectWorkerPictureByAccept(accept);
                String evaluateMissionName = accept.getWorkerName() + "_" + mission.getName();
                System.out.println(accept.getWorkerName());
                System.out.println(accept.getMissionName());
                Evaluate evaluate = evaluateService.getEvaluateByMission(evaluateMissionName);
                int count = 0;
                if (evaluate != null){
                    List<EvaluatePicture> evaluatePictures = evaluatePictureService.selectEvaluatePictureByRaterAndMission(evaluate.getRaterName(), accept.getMissionName());
                    for (int j = 0; j < evaluatePictures.size(); j++) {
                        EvaluatePicture evaluatePicture = evaluatePictures.get(j);
                        if (evaluatePicture.getIsRight() == 1)
                            count++;
                    }
                }

                ret += "{\"missionName\":\"" + mission.getName() + "\", \"workerName\":\"" + accept.getWorkerName()
                        + "\", \"points\":\"" + mission.getPoints() + "\", \"start\":\"" + accept.getStart() + "\", \"end\":\""
                        + accept.getEnd() + "\", \"way\":\"" + mission.getWay() + "\", \"type\":\"" + mission.getType()
                        + "\", \"description\":\"" + mission.getDescription() + "\", \"difficulty\":\"" + mission.getDifficultyClass()
                        + "\", \"isFinished\":\"" + accept.getIsFinished() + "\", \"numOfRight\":\"" + count + "\", \"numOfPictures\":\""
                        + workerPictures.size() + "\"}";
                if (i != accepts.size() - 1)
                    ret += "_";

        }
        return ret;
    }

    @RequestMapping(value = "/viewAccept/{missionName}/{workerName}/draw", method = GET)
    @ResponseBody
    public String viewAccept(@PathVariable("username") String username, @PathVariable("missionName") String missionName,
                             @PathVariable("workerName") String workerName){
        Accept accept = acceptService.getAcceptByMissionNameAndWorkerName(missionName, workerName);
        Mission mission = missionService.getMissionByName(accept.getMissionName());
        int pics = missionPictureService.selectMissionPicturesByMission(mission.getName()).size();
        String ret = "{\"announcerName\":\"" + username + "\", \"workerName\":\"" + accept.getWorkerName()
                + "\", \"points\":\"" + mission.getPoints() + "\", \"start\":\"" + accept.getStart() + "\", \"end\":\""
                + accept.getEnd() + "\", \"way\":\"" + mission.getWay() + "\", \"type\":\"" + mission.getType()
                + "\", \"description\":\"" + mission.getDescription() + "\", \"difficulty\":\"" + mission.getDifficultyClass()
                + "\", \"isFinished\":\"" + accept.getIsFinished() + "\", \"pics\":\"" + pics + "\"}";
        return ret;
    }

    /**
     * 查看一个工人提交任务后的图片
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/viewAcceptPictures/{missionName}/{workerName}/draw/{order}", method = GET)
    public String viewWorkerPictures(@PathVariable("username") String username, @PathVariable("missionName") String missionName,
                                     @PathVariable("workerName") String workerName, @PathVariable("order") String order) {
        Accept accept = acceptService.getAcceptByMissionNameAndWorkerName(missionName, workerName);
        int o = Integer.parseInt(order);
        WorkerPicture workerPicture = workerPictureService.selectWorkerPictureByAccept(accept).get(o);
        byte[] bytes = workerPicture.getPicture();
        Base64.Encoder encoder = Base64.getEncoder();
        String str = encoder.encodeToString(bytes);
        return str;
    }

    @RequestMapping(value = "/viewMissionPictures/{missionName}/{order}", method = GET)
    @ResponseBody
    public String viewMissionPictures(@PathVariable("username") String username, @PathVariable("missionName") String missionName,
                                      @PathVariable("order") String order){
        Mission mission = missionService.getMissionByName(missionName);
        int o = Integer.parseInt(order);
        MissionPicture missionPicture = missionPictureService.selectMissionPicturesByMission(mission.getName()).get(o);
        byte[] bytes = missionPicture.getPicture();
        Base64.Encoder encoder = Base64.getEncoder();
        String str = encoder.encodeToString(bytes);
        return str;
    }

    @RequestMapping(value = "/{workername}/loadDrawXY/{missionName}/{order}", method = GET)
    @ResponseBody
    public String loadXY(@PathVariable("username") String username, @PathVariable("username") String workerName,
                         @PathVariable("missionName") String missionName, @PathVariable("order") String order){
        Mission mission = missionService.getMissionByName(missionName);
        List<MissionPicture> missionPictures = missionPictureService.selectMissionPicturesByMission(missionName);
        int o = Integer.parseInt(order);
        MissionPicture missionPicture = missionPictures.get(o);
        String xy = missionPicture.getName();
        if(xy.contains(",")){
            return xy;
        }
        else
            return "kong";
    }



}
